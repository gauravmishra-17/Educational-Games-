import axios from "axios";

const USER_API_BASE_URL = "http://localhost:8080/users";

class UserService {
  registerUser(user) {
    return axios.post(USER_API_BASE_URL + "/register", user);
  }

  loginUser(user) {
    return axios.post(USER_API_BASE_URL + "/authenticate", user);
  }
  getUsers() {
    return axios.get(USER_API_BASE_URL + "/all");
  }

  createUser(user) {
    return axios.post(USER_API_BASE_URL + "/admin", user);
  }

  getUserById(userId) {
    return axios.get(USER_API_BASE_URL + "/" + userId);
  }

  updateUser(user, userId) {
    return axios.put(USER_API_BASE_URL + "/admin/" + userId, user);
  }

  deleteUser(userId) {
    return axios.delete(USER_API_BASE_URL + "/admin/" + userId);
  }
}

export default new UserService();
