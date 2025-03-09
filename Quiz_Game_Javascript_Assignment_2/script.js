// I use the local storage to store the best score as the result even if the user refreshes the page.the best score is stored in the local storage and is displayed on the index page.
//questions: Array to store fetched questions.
//current: Tracks the current question index.
// score: Tracks the user's score.
//timer: Stores the timer interval ID.
//difficulty: Stores the selected difficulty level.
const ls = localStorage;
let questions = [], current = 0, score = 0, timer, difficulty;


// Defines valid categories for autocomplete. Used to suggest categories as the user types.
const categories = [
    "General Knowledge",
    "Science: Computers",
    "Sports",
    "History"
];

//Loads best scores for each difficulty level.Retrieves best scores from localStorage and displays them.
if (document.getElementById('start')) {
    ['easy', 'medium', 'hard'].forEach(diff => {
        document.getElementById(`${diff}Best`).textContent = ls.getItem(`${diff}Best`) || 0;
    });

// Provides autocomplete suggestions for category input. Filters categories based on user input. Displays matching categories as clickable suggestions.

    document.getElementById('catInput').addEventListener('input', function(e) {
        const input = this.value.toLowerCase();
        const suggestions = document.getElementById('catSuggestions');
        suggestions.innerHTML = '';
        
        const matches = categories.filter(category => category.toLowerCase().includes(input));
        
        matches.forEach(match => {
            const div = document.createElement('div');
            div.className = 'autocomplete-item';
            div.textContent = match;
            div.addEventListener('click', () => {
                this.value = match;
                suggestions.innerHTML = '';
            });
            suggestions.appendChild(div);
        });
    });

//Handles the "Start Quiz" button click. Validates the selected category. Maps category names to API IDs. Redirects to the quiz page with category and difficulty parameters.

    document.getElementById('start').addEventListener('click', () => {
        const category = document.getElementById('catInput').value.toLowerCase();
        difficulty = document.getElementById('diff').value.toLowerCase();
        
        const validCategories = categories.map(c => c.toLowerCase());
        if (!validCategories.includes(category)) {
            document.getElementById('catError').classList.remove('d-none');
            return;
        }
        
        const categoryMap = {
            'general knowledge': 9,
            'science: computers': 18,
            'sports': 21,
            'history': 23
        };
        
        location.href = `quiz.html?cat=${categoryMap[category]}&diff=${difficulty}`;
    });
}

// Loads questions from the API when the quiz page loads. Fetches questions based on category and difficulty. Stores questions in the questions array. Displays the first question.

if (document.getElementById('qText')) {
    (async function loadQuestions() {
        try {
            const params = new URLSearchParams(location.search);
            difficulty = params.get('diff');
            document.body.classList.add(difficulty);
            
            const response = await fetch(`https://opentdb.com/api.php?amount=10&category=${params.get('cat')}&difficulty=${difficulty}&type=multiple`);
            const data = await response.json();
            
            if (data.response_code !== 0) throw new Error('No questions found');
            
            questions = data.results.map(q => ({
                question: q.question,
                correct: q.correct_answer,
                options: [...q.incorrect_answers, q.correct_answer].sort(() => Math.random() - 0.5)
            }));
            showQuestion();
        } catch (error) {
            location.href = 'index.html';
        }
    })();

//Displays the current question and options. Updates the question number. Creates buttons for each answer option. Starts the timer.

    function showQuestion() {
        document.getElementById('qNum').textContent = current + 1;
        const q = questions[current];
        document.getElementById('qText').innerHTML = q.question;
        const optionsDiv = document.getElementById('options');
        optionsDiv.innerHTML = '';
        
        q.options.forEach(opt => {
            const btn = document.createElement('button');
            btn.className = 'btn btn-light text-start py-3 mb-2';
            btn.innerHTML = opt;
            btn.onclick = () => checkAnswer(opt === q.correct);
            optionsDiv.appendChild(btn);
        });
        
        startTimer();
    }

//Implements a 15-second countdown timer. Updates the timer display every second. Reveals the answer and moves to the next question when time runs out.

    function startTimer() {
        let time = 15;
        document.getElementById('timer').textContent = time;
        timer = setInterval(() => {
            time--;
            document.getElementById('timer').textContent = time;
            if (time <= 0) {
                clearInterval(timer);
                revealAnswer();
                setTimeout(nextQuestion, 3000);
            }
        }, 1000);
    }

//Checks if the selected answer is correct. Updates the score if the answer is correct. Reveals the correct answer and moves to the next question.

    function checkAnswer(correct) {
        clearInterval(timer);
        if (correct) score++;
        document.getElementById('cScore').textContent = score;
        revealAnswer(correct);
        setTimeout(nextQuestion, 3000);
    }

//Highlights the correct and incorrect answers. Disables all answer buttons. Adds visual feedback for correct and incorrect answers.

    function revealAnswer(isCorrect) {
        const correctAnswer = questions[current].correct;
        document.querySelectorAll('#options button').forEach(btn => {
            btn.disabled = true;
            if (btn.innerHTML === correctAnswer) {
                btn.classList.add('correct-answer');
            } else if (!isCorrect) {
                btn.classList.add('wrong-answer');
            }
        });
    }

// Moves to the next question or ends the game. Increments the question index. Displays the next question or ends the game if all questions are answered.

    function nextQuestion() {
        current++;
        if (current < 10) showQuestion();
        else endGame();
    }

//Displays the end screen with the final score. Updates the best score if the current score is higher. Shows a celebration animation if the score is 5 or higher. Provides options to play again or return to the main menu.

    function endGame() {
        const bestKey = `${difficulty}Best`;
        if (score > (ls.getItem(bestKey) || 0)) ls.setItem(bestKey, score);
        
        const celebration = score >= 5;
        if (celebration) confetti({ particleCount: 150, spread: 100 });
        
        document.body.innerHTML = `
            <div class="container d-flex min-vh-100 justify-content-center align-items-center">
                <div class="text-center p-5 rounded-4 ${celebration ? 'celebration bg-success' : 'loss bg-danger'}">
                    <h1 class="display-4 fw-bold mb-4">${celebration ? '🎉 You Won!' : '💥 Try Again'}</h1>
                    <h3 class="mb-4">Score: ${score}/10</h3>
                    <div class="d-flex gap-3 justify-content-center">
                        <button class="btn btn-light px-4" onclick="location.reload()">Play Again</button>
                        <button class="btn btn-outline-light px-4" onclick="location.href='index.html'">Main Menu</button>
                    </div>
                </div>
            </div>
        `;
    }
}


/*
Summary
-> This code implements a fully functional quiz game with:

-> Autocomplete for category selection.

-> Difficulty-based background colors.

-> A 15-second timer for each question.

-> Score tracking and best scores.

-> Celebration animations for high scores.

-> Responsive design and smooth transitions.
*/
