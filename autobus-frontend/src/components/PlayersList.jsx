import "./PlayersList.css";

function PlayersList({ players }) {
  if (players.length === 0) {
    return null;
  }

  return (
    <div className="players-card">
      <div className="players-header">
        <h3>Players</h3>
        <span>{players.length} joined</span>
      </div>

      <div className="players-list">
        {players.map((player, index) => (
          <div className="player-item" key={index}>
            <div className="player-avatar">
              {player.host ? "👑" : "🙂"}
            </div>

            <div className="player-info">
              <strong>{player.name}</strong>
              <span>{player.host ? "Host" : "Player"}</span>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default PlayersList;