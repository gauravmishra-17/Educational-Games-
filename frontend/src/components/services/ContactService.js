import axios from "axios";

const GAME_API_BASE_URL = "http://localhost:8080/contacts";

class ContactService{

    getContacts(){

        return axios.get(GAME_API_BASE_URL + "/all");
    }

    createGame(contact){
        return axios.post(GAME_API_BASE_URL + "/admin", contact);
    }

    getGameByEmail(email){
        return axios.get(GAME_API_BASE_URL + '/admin/' + email);
    }
}
export default new ContactService();
