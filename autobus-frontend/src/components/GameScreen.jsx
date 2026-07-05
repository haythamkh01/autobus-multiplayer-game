import { useEffect, useState } from "react";
import "./GameScreen.css";

const fields = ["name", "country", "animal", "plant", "job"];

function GameScreen({
  letter,
  currentRound,
  maxRounds,
  isHost,
  submittedAnswers,
  submitAnswers,
  finishRound,
  scoreboard,
  nextRound,
  endAnswering,
}) {
  const [answers, setAnswers] = useState({
    name: "",
    country: "",
    animal: "",
    plant: "",
    job: "",
  });

  const [submitted, setSubmitted] = useState(false);
  const [scores, setScores] = useState({});
  
  const [timeLeft, setTimeLeft] = useState(60);


  useEffect(() => {
    setSubmitted(false);
    setScores({});
    setTimeLeft(60);
    setAnswers({
      name: "",
      country: "",
      animal: "",
      plant: "",
      job: "",
      
    });
  }, [letter]);

  useEffect(() => {
  if (submitted || scoreboard || submittedAnswers.length > 0) {
    return;
  }

  if (timeLeft <= 0) {
    return;
  }

  const timer = setTimeout(() => {
    setTimeLeft((prev) => prev - 1);
  }, 1000);

  return () => clearTimeout(timer);
}, [timeLeft, submitted, scoreboard, submittedAnswers.length]);

  function handleChange(e) {
    setAnswers({
      ...answers,
      [e.target.name]: e.target.value,
    });
  }

  function handleSubmit() {
    submitAnswers(answers);
    setSubmitted(true);
  }

  function giveScore(playerName, field, points) {
    setScores((prev) => ({
      ...prev,
      [playerName]: {
        ...prev[playerName],
        [field]: points,
      },
    }));
  }

  function getPlayerTotal(playerName) {
    return Object.values(scores[playerName] || {}).reduce(
      (sum, value) => sum + value,
      0
    );
  }

  return (
    <div className="game-page">
      <div className="letter-card">
        <div className="round-badge">
  Round {currentRound} / {maxRounds}
</div>

        <p>Current Letter</p>
        <h1>{letter}</h1>
        <div className="timer-badge">
          Time Left: {timeLeft} seconds
        </div>
      </div>

      {!submitted && (
        <div className="answer-card">
          <h2>Your Answers</h2>

          {fields.map((field) => (
            <input
              key={field}
              name={field}
              value={answers[field]}
              placeholder={field}
              onChange={handleChange}
            />
          ))}

          <button className="submit-btn" onClick={handleSubmit}>
            Submit Answers
          </button>
        </div>
      )}

      {submitted && !scoreboard && submittedAnswers.length === 0 && (
        <div className="waiting-card">
          <h2>✅ Answers submitted</h2>
          <p>Waiting for all players...</p>
        </div>
      )}

      {isHost && submitted && submittedAnswers.length === 0 && !scoreboard && (
  <button className="end-answering-btn" onClick={endAnswering}>
     End Answering
  </button>
)}

      { submittedAnswers.length > 0 && !scoreboard && (
        <div className="review-card">
          <h2>
  {isHost
    ? "Review Answers"
    : "Waiting for Host Review..."}
</h2>

          {submittedAnswers.map((item, index) => (
            <div className="answer-review" key={index}>
              <h3>{item.playerName}</h3>

              {fields.map((field) => (
                <div className="review-row" key={field}>
                  <span>
                    <strong>{field}</strong>: {item.answers[field]}
                  </span>

                  <div className="score-buttons">
  <button
    disabled={!isHost}
    onClick={() => giveScore(item.playerName, field, 10)}
  >
    ✅
  </button>

  <button
    disabled={!isHost}
    onClick={() => giveScore(item.playerName, field, 5)}
  >
    ➖
  </button>

  <button
    disabled={!isHost}
    onClick={() => giveScore(item.playerName, field, 0)}
  >
    ❌
  </button>

  <b>{scores[item.playerName]?.[field] ?? "-"}</b>
</div>
                </div>
              ))}

              <p className="player-total">
                Total: {getPlayerTotal(item.playerName)}
              </p>
            </div>
          ))}

          {isHost && (
  <button
    className="finish-btn"
    onClick={() => finishRound(scores)}
  >
    Finish Round
  </button>
)}
        </div>
      )}

      {scoreboard && (
        <div className="scoreboard-card">
          <h2>Scoreboard</h2>

          {Object.entries(scoreboard)
            .sort((a, b) => b[1] - a[1])
            .map(([player, score], index) => (
              <p key={player}>
                {index === 0 ? "🥇" : index === 1 ? "🥈" : index === 2 ? "🥉" : "🎮"}{" "}
                {player}: {score}
              </p>
            ))}

{isHost && (
  <button className="next-btn" onClick={nextRound}>
    {currentRound === maxRounds
      ? "🏆 Finish Game"
      : "➡️ Next Round"}
  </button>
)}
        </div>
      )}
    </div>
  );
}

export default GameScreen;