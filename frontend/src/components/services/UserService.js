import axios from "axios";

const USER_API_BASE_URL = "http://localhost:8080/users";
const token =localStorage.getItem("JwtToken");


class UserService {
  registerUser(user) {
    return axios.post(USER_API_BASE_URL + "/register", user);
  }

  loginUser(user) {
    return axios.post(USER_API_BASE_URL + "/authenticate", user);
  }
  getUsers() {
    return axios.get(USER_API_BASE_URL + "/admin/all",{ headers: {"Authorization" : `Bearer ${token}`}});
  }

  createUser(user) {
    return axios.post(USER_API_BASE_URL + "/admin", user, { headers: {"Authorization" : `Bearer ${token}`}});
  }

  getUserById(userId) {
    return axios.get(USER_API_BASE_URL + "/" + userId, { headers: {"Authorization" : `Bearer ${token}`}});
  }

  updateUser(user, userId) {
    return axios.put(USER_API_BASE_URL + "/admin/" + userId, user,{ headers: {"Authorization" : `Bearer ${token}`}});
  }

  deleteUser(userId) {
    return axios.delete(USER_API_BASE_URL + "/admin/" + userId,{ headers: {"Authorization" : `Bearer ${token}`}});
  }
}

export default new UserService();
