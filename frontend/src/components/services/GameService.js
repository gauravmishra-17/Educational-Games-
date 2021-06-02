import axios from "axios";

const GAME_API_BASE_URL = "http://localhost:8080/games";

class GameService  {

    getGames(){

        return axios.get(GAME_API_BASE_URL + "/all");
    }

    createGame(game){
        return axios.post(GAME_API_BASE_URL + "/admin", game);
    }

    getGameById(gameId){
        return axios.get(GAME_API_BASE_URL + '/' + gameId);
    }

    updateGame(game, gameId){
        return axios.put(GAME_API_BASE_URL + '/admin/' + gameId, game);
    }

    deleteGame(gameId){
        return axios.delete(GAME_API_BASE_URL + '/admin/' + gameId);
    }
    
}

export default new GameService();
