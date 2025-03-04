let currentPlayer = 1;
let scores = { 1: { saved: 0, current: 0 }, 2: { saved: 0, current: 0 } };
let diceImage = document.getElementById("diceImage");

document.getElementById("rollDice").addEventListener("click", rollDice);
document.getElementById("saveScore").addEventListener("click", saveScore);
document.getElementById("resetGame").addEventListener("click", resetGame);

function rollDice() {
    let diceRoll = Math.floor(Math.random() * 6) + 1;
    if(diceRoll === 1) {
        diceImage.src = `https://cdn-icons-png.flaticon.com/128/10826/10826863.png`;
    }
    else if(diceRoll === 2) {
        diceImage.src = `https://cdn-icons-png.flaticon.com/128/10826/10826864.png`;
    }
    else if(diceRoll === 3) {
        diceImage.src = `https://cdn-icons-png.flaticon.com/128/10826/10826865.png`;
    }
    else if(diceRoll === 4) {
        diceImage.src = `https://cdn-icons-png.flaticon.com/128/10826/10826866.png`;
    }
    else if(diceRoll === 5) {
        diceImage.src = `https://cdn-icons-png.flaticon.com/128/1626/1626822.png`;
    }
    else if(diceRoll === 6) {
        diceImage.src = `https://cdn-icons-png.flaticon.com/128/10826/10826868.png`;
    }

    if (diceRoll === 1) {
        scores[currentPlayer].current = 0;
        switchTurn();  // Automatically change the turn
    } else {
        scores[currentPlayer].current += diceRoll;
    }

    updateUI();
}

function saveScore() {
    scores[currentPlayer].saved += scores[currentPlayer].current;
    scores[currentPlayer].current = 0;

    if (scores[currentPlayer].saved >= 20) {
        document.getElementById("winner").innerText = 
            `${document.getElementById(`name${currentPlayer}`).value} Wins!`;
        disableButtons();
    } else {
        switchTurn();
    }

    updateUI();
}

function switchTurn() {
    currentPlayer = currentPlayer === 1 ? 2 : 1;
}

function resetGame() {
    scores = { 1: { saved: 0, current: 0 }, 2: { saved: 0, current: 0 } };
    currentPlayer = 1;
    document.getElementById("winner").innerText = "";
    enableButtons();
    updateUI();
}

function updateUI() {
    document.getElementById("saved1").innerText = scores[1].saved;
    document.getElementById("current1").innerText = scores[1].current;
    document.getElementById("saved2").innerText = scores[2].saved;
    document.getElementById("current2").innerText = scores[2].current;
}

function disableButtons() {
    document.getElementById("rollDice").disabled = true;
    document.getElementById("saveScore").disabled = true;
}

function enableButtons() {
    document.getElementById("rollDice").disabled = false;
    document.getElementById("saveScore").disabled = false;
}
