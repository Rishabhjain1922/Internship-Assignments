Quiz Game
Overview
Quiz Game is an interactive web-based quiz application where users can test their knowledge across various categories and difficulty levels. The game features a timer, score tracking, and dynamic feedback for correct and incorrect answers. It also includes celebration animations for high scores and a responsive design for both desktop and mobile devices.

Features
Category Selection:

Choose from categories like General Knowledge, Science: Computers, Sports, and History.

Autocomplete suggestions for category input.

Difficulty Levels:

Three difficulty levels: Easy, Medium, and Hard.

Difficulty-based background colors (green for Easy, yellow for Medium, red for Hard).

Question Display:

Each question has four multiple-choice options.

Questions are fetched from the Open Trivia Database API.

Timer:

15-second timer for each question.

Progress bar visually indicates remaining time.

Score Tracking:

Tracks the user's score in real-time.

Stores the best score for each difficulty level in localStorage.

Answer Feedback:

Highlights correct and incorrect answers.

Automatically reveals the correct answer if the user doesn't respond in time.

Celebration Effects:

Confetti animation for scores of 5 or higher.

Shake animation for scores below 5.

Responsive Design:

Works seamlessly on both desktop and mobile devices.

End Screen:

Displays the final score.

Provides options to play again or return to the main menu.

Technologies Used
Frontend:

HTML, CSS, JavaScript

Bootstrap for styling and layout

API:

Open Trivia Database API for fetching questions

Libraries:

Canvas Confetti for celebration animations

How to Play
Start Screen:

Enter a category name (e.g., "General Knowledge").

Select a difficulty level (Easy, Medium, or Hard).

Click "Start Quiz."

Game Screen:

Answer each question within 15 seconds.

Select the correct option to earn points.

If you don't answer in time, the correct answer will be revealed.

End Screen:

View your final score.

Choose to play again or return to the main menu.

Setup Instructions
Clone the Repository:

git clone https://github.com/Rishabhjain1922/Quiz_Game_Js-2.git
cd quiz-game
Open the Project:

Open the index.html file in your browser to start the game.

Run Locally:

No server or backend setup is required. The game runs entirely in the browser.


quiz-game/
├── index.html          # Home page with category and difficulty selection
├── quiz.html           # Quiz page with questions and timer
├── style.css           # Custom styles for the game
├── script.js           # JavaScript logic for the game
├── README.md           # Project documentation
API Usage
The game uses the Open Trivia Database API to fetch questions. The API endpoint is:

https://opentdb.com/api.php?amount=10&category={category_id}&difficulty={difficulty}&type=multiple
category_id: Numeric ID for the selected category.

difficulty: Easy, Medium, or Hard.

Future Improvements
Add more categories and difficulty levels.

Implement a leaderboard for global high scores.

Add sound effects for correct/incorrect answers.

Allow users to create custom quizzes.

Credits
Open Trivia Database: https://opentdb.com/

Bootstrap: https://getbootstrap.com/

Canvas Confetti: https://www.kirilv.com/canvas-confetti/

License
This project is licensed under the MIT License. See the LICENSE file for details.

Enjoy playing the Quiz Game! 🎉