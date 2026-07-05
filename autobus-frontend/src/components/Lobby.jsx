import "./Lobby.css";

function Lobby({
  roomCode,
  setRoomCode,
  playerName,
  setPlayerName,
  createRoom,
  connectAndJoin,
}) {
  return (
    <div className="lobby">
      <div className="lobby-card">
        <div className="lobby-header">
          <span className="game-icon">🚌</span>
          <h2>Autobus Game</h2>
          <p>Create a room or join your friends</p>
        </div>

        <div className="form-group">
          <label>Your name</label>
          <input
            value={playerName}
            onChange={(e) => setPlayerName(e.target.value)}
            placeholder="Enter your name"
          />
        </div>

        <button className="primary-btn" onClick={createRoom}>
          Create Room
        </button>

        <div className="divider">
          <span>or join existing room</span>
        </div>

        <div className="form-group">
          <label>Room code</label>
          <input
            value={roomCode}
            onChange={(e) => setRoomCode(e.target.value)}
            placeholder="Enter room code"
          />
        </div>

        <button className="secondary-btn" onClick={connectAndJoin}>
          Join Room
        </button>

        {roomCode && (
          <div className="room-code-box">
            <span>Room Code</span>
            <strong>{roomCode}</strong>
            <small>Share this code with your friends</small>
          </div>
        )}
      </div>
    </div>
  );
}

export default Lobby;