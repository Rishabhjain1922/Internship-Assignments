let currentPlayer = 1;
let scores = { 1: { saved: 0, current: 0 }, 2: { saved: 0, current: 0 } };
const diceImage = document.getElementById("diceImage");
const playerCards = document.querySelectorAll('.player-card');

// Event Listeners
document.getElementById("rollDice").addEventListener("click", rollDice);
document.getElementById("saveScore").addEventListener("click", saveScore);
document.getElementById("resetGame").addEventListener("click", resetGame);

function updateActivePlayer() {
    playerCards.forEach(card => card.classList.remove('active'));
    document.getElementById(`player${currentPlayer}`).classList.add('active');
}

async function rollDice() {
    diceImage.classList.add('dice-roll-animation');
    await new Promise(resolve => setTimeout(resolve, 1000));
    
    const diceRoll = Math.floor(Math.random() * 6) + 1;
    if(diceRoll === 1) {
        diceImage.src = "https://cdn-icons-png.flaticon.com/128/10826/10826863.png";
    }
    else if(diceRoll === 2) {
        diceImage.src = "https://cdn-icons-png.flaticon.com/128/10826/10826864.png";
    }
    else if(diceRoll === 3) {
        diceImage.src = "https://cdn-icons-png.flaticon.com/128/10826/10826865.png";
    }
    else if(diceRoll === 4) {
        diceImage.src = "https://cdn-icons-png.flaticon.com/128/10826/10826866.png";
    }
    else if(diceRoll === 5) {
        diceImage.src = "https://cdn-icons-png.flaticon.com/128/1626/1626822.png";
    }
    else if(diceRoll === 6) {
        diceImage.src = "https://cdn-icons-png.flaticon.com/128/10826/10826868.png";
    }

    if (diceRoll === 1) {
        scores[currentPlayer].current = 0;
        switchTurn();
    } else {
        scores[currentPlayer].current += diceRoll;
    }

    updateUI();
    diceImage.classList.remove('dice-roll-animation');
}

function saveScore() {
    scores[currentPlayer].saved += scores[currentPlayer].current;
    scores[currentPlayer].current = 0;

    if (scores[currentPlayer].saved >= 100) {
        const winnerName = document.getElementById(`name${currentPlayer}`).value;
        document.getElementById("winner").innerHTML = `
            🏆 ${winnerName} Wins! 🎉
            <div class="sparkles">✨✨✨</div>
        `;
        disableButtons();
        confettiEffect();
    } else {
        switchTurn();
    }

    updateUI();
}

function switchTurn() {
    currentPlayer = currentPlayer === 1 ? 2 : 1;
    updateActivePlayer();
}

function resetGame() {
    scores = { 1: { saved: 0, current: 0 }, 2: { saved: 0, current: 0 } };
    currentPlayer = 1;
    document.getElementById("winner").innerHTML = "";
    diceImage.src = "https://cdn-icons-png.flaticon.com/128/10826/10826863.png";
    enableButtons();
    updateUI();
    updateActivePlayer();
}

function updateUI() {
    document.getElementById("saved1").textContent = scores[1].saved;
    document.getElementById("current1").textContent = scores[1].current;
    document.getElementById("saved2").textContent = scores[2].saved;
    document.getElementById("current2").textContent = scores[2].current;
}

function disableButtons() {
    document.getElementById("rollDice").disabled = true;
    document.getElementById("saveScore").disabled = true;
}

function enableButtons() {
    document.getElementById("rollDice").disabled = false;
    document.getElementById("saveScore").disabled = false;
}

function confettiEffect() {
    const colors = ['#ff0000', '#00ff00', '#0000ff', '#ffff00', '#ff00ff'];
    for (let i = 0; i < 50; i++) {
        const confetti = document.createElement('div');
        confetti.style.position = 'fixed';
        confetti.style.width = '10px';
        confetti.style.height = '10px';
        confetti.style.backgroundColor = colors[Math.floor(Math.random() * colors.length)];
        confetti.style.left = Math.random() * 100 + 'vw';
        confetti.style.top = '-10px';
        confetti.style.borderRadius = '50%';
        confetti.style.animation = `fall ${Math.random() * 3 + 2}s linear`;
        document.body.appendChild(confetti);

        setTimeout(() => confetti.remove(), 5000);
    }
}

// Initialize game
updateActivePlayer();