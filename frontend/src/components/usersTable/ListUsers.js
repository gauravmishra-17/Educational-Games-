import React, { Component } from "react";
import UserService from "../services/UserService";
import "./listProducts.css";

class ListUsers extends Component {
  constructor(props) {
    super(props);
    this.state = {
      users: [],
    };
    this.addUser = this.addUser.bind(this);
    this.editUser = this.editUser.bind(this);
    this.deleteUser = this.deleteUser.bind(this);
    this.viewUserDetails = this.viewUserDetails.bind(this);
  }
  componentDidMount() {
    UserService.getUsers().then((res) => {
      this.setState({ users: res.data });
    });
  }
  // configure the route component to add-game endpoint
  addUser() {
    this.props.history.push("/register/add-user");
  }

  editUser(id) {
    this.props.history.push(`/register/${id}`);
  }

  deleteUser(id) {
    UserService.deleteUser(id).then((res) => {
      this.setState({
        users: this.state.users.filter((user) => user.id !== id),
      });
    });
  }

  viewUserDetails(id) {
    this.props.history.push(`/view-user/${id}`);
  }

  render() {
    return (
      <div>
        <h2 className={"text-center title"}>Users</h2>
        <div className={"row"}>
          <button className={"btn btn-primary addGame"} onClick={this.addUser}>
            {" "}
            Add Users{" "}
          </button>
        </div>
        <div className={"container displayGamesList"}>
          <div className={"table-responsive-sm table-responsive-md w-auto"}>
            <table>
              <thead>
                <tr>
                  <th> Username </th>
                  <th> Name </th>
                  <th> Email </th>
                  <th> Actions </th>
                </tr>
              </thead>
              <tbody>
                {this.state.users.map((user) => (
                  <tr key={user.id}>
                    <td className={"td"}> {user.username} </td>
                    <td className={"td"}>
                      {" "}
                      {user.firstname + " " + user.lastname}{" "}
                    </td>
                    <td className={"td"}>{user.email}</td>
                    <td>
                      <button
                        onClick={() => this.editUser(user.id)}
                        className={"btn btn-info bt"}
                      >
                        Update
                      </button>
                      <button
                        onClick={() => this.deleteUser(user.id)}
                        className={"btn btn-danger bt"}
                      >
                        Delete
                      </button>
                      <button
                        onClick={() => this.viewUserDetails(user.id)}
                        className={"btn btn-secondary bt"}
                      >
                        Details
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    );
  }
}

export default ListUsers;
