import { useRef, useState } from "react";
import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client/dist/sockjs";

import Lobby from "./components/Lobby";
import PlayersList from "./components/PlayersList";
import GameScreen from "./components/GameScreen";
import "./App.css";

function App() {
  const [roomCode, setRoomCode] = useState("");
  const [playerName, setPlayerName] = useState("");
  const [players, setPlayers] = useState([]);

  const [isHost, setIsHost] = useState(false);
  const [gameStarted, setGameStarted] = useState(false);
  const [gameFinished, setGameFinished] = useState(false);

  const [letter, setLetter] = useState("");
  const [submittedAnswers, setSubmittedAnswers] = useState([]);
  const [scoreboard, setScoreboard] = useState(null);

  const [winnerName, setWinnerName] = useState("");
  const [finalScores, setFinalScores] = useState(null);

  const clientRef = useRef(null);

  const [currentRound, setCurrentRound] = useState(1);
  const [maxRounds, setMaxRounds] = useState(3);

  function handleSocketMessage(data) {
    if (data.type === "PLAYERS_UPDATED") {
      setPlayers(data.players);
      return;
    }

    if (data.type === "GAME_STARTED") {
      setGameStarted(true);
      setGameFinished(false);
      setLetter(data.letter);
      setSubmittedAnswers([]);
      setScoreboard(null);
      setCurrentRound(data.currentRound);
      setMaxRounds(data.maxRounds);
      return;
    }

    if (data.type === "ANSWERS_UPDATED") {
      setSubmittedAnswers(data.submittedAnswers);
      return;
    }

    if (data.type === "SCOREBOARD_UPDATED") {
      setScoreboard(data.totalScores);
      return;
    }

    if (data.type === "GAME_FINISHED") {
      setGameFinished(true);
      setWinnerName(data.winnerName);
      setFinalScores(data.totalScores);
    }
  }

  function createClient(code, onConnected) {
    const client = new Client({
      webSocketFactory: () => new SockJS("https://autobus-backend1.onrender.com/ws"),
      onConnect: () => {
        client.subscribe(`/topic/room/${code}`, (response) => {
          handleSocketMessage(JSON.parse(response.body));
        });

        if (onConnected) {
          onConnected(client);
        }
      },
    });

    client.activate();
    clientRef.current = client;
  }

  function connectToRoom(code) {
    if (clientRef.current && clientRef.current.connected) return;
    createClient(code);
  }

  function connectAndJoin() {
    if (!roomCode || !playerName) {
      alert("Enter room code and player name");
      return;
    }

    if (clientRef.current && clientRef.current.connected) {
      alert("Already connected");
      return;
    }

    createClient(roomCode, (client) => {
      client.publish({
        destination: "/app/join-room",
        body: JSON.stringify({ roomCode, playerName }),
      });
    });
  }

  async function createRoom() {
    if (!playerName) {
      alert("Enter your name first");
      return;
    }

    const response = await fetch("https://autobus-backend1.onrender.com/api/rooms", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ playerName }),
    });

    const data = await response.json();

    setRoomCode(data.roomCode);
    setIsHost(true);
    setPlayers([{ name: playerName, host: true }]);

    connectToRoom(data.roomCode);
  }

  function startGame() {
    if (!clientRef.current || !clientRef.current.connected) {
      alert("Not connected");
      return;
    }

    clientRef.current.publish({
      destination: "/app/start-game",
      body: JSON.stringify({ roomCode }),
    });
  }

  function submitAnswers(answers) {
    clientRef.current.publish({
      destination: "/app/submit-answers",
      body: JSON.stringify({
        roomCode,
        playerName,
        answers,
      }),
    });
  }

  function finishRound(scores) {
    clientRef.current.publish({
      destination: "/app/finish-round",
      body: JSON.stringify({
        roomCode,
        scores,
      }),
    });
  }

  function nextRound() {
    setScoreboard(null);
    setSubmittedAnswers([]);

    clientRef.current.publish({
      destination: "/app/next-round",
      body: JSON.stringify({ roomCode }),
    });
  }

  const sortedFinalScores = Object.entries(finalScores || {}).sort(
    (a, b) => b[1] - a[1]
  );
  function endAnswering() {
  clientRef.current.publish({
    destination: "/app/end-answering",
    body: JSON.stringify({
      roomCode,
    }),
  });
}

  return (
    <div className="app">
      {!gameStarted && (
        <>
          <Lobby
            roomCode={roomCode}
            setRoomCode={setRoomCode}
            playerName={playerName}
            setPlayerName={setPlayerName}
            createRoom={createRoom}
            connectAndJoin={connectAndJoin}
          />

          <PlayersList players={players} />

          {isHost && players.length > 0 && (
            <button className="start-game-btn" onClick={startGame}>
              Start Game
            </button>
          )}
        </>
      )}

      {gameStarted && !gameFinished && (
        <GameScreen
  letter={letter}
  currentRound={currentRound}
  maxRounds={maxRounds}
  isHost={isHost}
  submittedAnswers={submittedAnswers}
  submitAnswers={submitAnswers}
  finishRound={finishRound}
  scoreboard={scoreboard}
  nextRound={nextRound}
  endAnswering={endAnswering}
  
/>
      )}

      {gameFinished && (
  <div className="winner-card">
    <div className="winner-icon">🏆</div>

    <p className="game-over-text">GAME OVER</p>

    <h1>Champion</h1>
    <h2 className="champion-name">{winnerName}</h2>

    <div className="final-scores">
      {sortedFinalScores.map(([player, score], index) => (
        <div
          className={`final-score-row ${
            index === 0 ? "first-place" : ""
          }`}
          key={player}
        >
          <span>
            {index === 0
              ? "🥇"
              : index === 1
              ? "🥈"
              : index === 2
              ? "🥉"
              : "🎮"}{" "}
            {player}
          </span>

          <strong>{score} pts</strong>
        </div>
      ))}
    </div>

    <button
      className="play-again-btn"
      onClick={() => window.location.reload()}
    >
      Play Again
    </button>
  </div>
)}
    </div>
  );
}

export default App;