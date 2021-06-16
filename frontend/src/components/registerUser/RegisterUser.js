import React, { Component } from "react";
import UserService from "../services/UserService";

class RegisterUser extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: this.props.match.params.id,
      email: "",
      firstName: "",
      lastname: "",
      username: "",
      password: "",
    };
    this.changeEmailHandler = this.changeEmailHandler.bind(this);
    this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
    this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
    this.changeUserNameHandler = this.changeUserNameHandler.bind(this);
    this.changePasswordHandler = this.changePasswordHandler.bind(this);
    this.registerUser = this.registerUser.bind(this);
    this.cancel = this.cancel.bind(this);
  }

  // step 3
  //   componentDidMount() {
  //     // step 4
  //     if (this.state.id === "_add") {
  //       return;
  //     } else {
  //       GameService.getGameById(this.state.id).then((res) => {
  //         let game = res.data;
  //         this.setState({
  //           title: game.title,
  //           description: game.description,
  //           pictureUrl: game.pictureUrl,
  //           genre: game.genre,
  //           gameUrl: game.gameUrl,
  //         });
  //       });
  //     }
  //   }

  registerUser = (e) => {
    e.preventDefault();
    let user = {
      email: this.state.email,
      firstname: this.state.firstName,
      lastname: this.state.lastname,
      username: this.state.username,
      password: this.state.password,
    };
    if (this.state.id === "add-user" || this.state.id === "register-user")
      UserService.registerUser(user).then((res) => {});
    else UserService.updateUser(user, this.state.id).then((res) => {});
  };
  changeEmailHandler = (e) => {
    this.setState({ email: e.target.value });
  };

  changeFirstNameHandler = (e) => {
    this.setState({ firstName: e.target.value });
  };

  changeLastNameHandler = (e) => {
    this.setState({ lastname: e.target.value });
  };

  changeUserNameHandler = (e) => {
    this.setState({ username: e.target.value });
  };
  changePasswordHandler = (e) => {
    this.setState({ password: e.target.value });
  };

  cancel() {
    if (this.state.id !== "register-user")
      this.props.history.push("/list-users");
    else this.props.history.push("/Home");
  }
  getTitle() {
    if (this.state.id !== null && this.state.id !== "register-user") {
      return <h3 className={"text-center"}> Add User </h3>;
    } else {
      return <h3 className={"text-center"}> Register </h3>;
    }
  }

  render() {
    return (
      <div>
        <div className={"container addForm"}>
          <div className={"row"}>
            <div className={"card col-md-6 offset-md-3 offset-md-3 "}>
              {this.getTitle()}
              <div className={"card-body"}>
                <form onSubmit={this.registerUser}>
                  <div className={"form-group"}>
                    <label> Enail: </label>
                    <input
                      className={"form-control"}
                      placeholder={"Email"}
                      name={"email"}
                      value={this.state.email}
                      onChange={this.changeEmailHandler}
                    />
                  </div>
                  <div className={"form-group"}>
                    <label> First Name: </label>
                    <input
                      className={"form-control"}
                      placeholder={"First Name"}
                      name={"firstname"}
                      value={this.state.firstName}
                      onChange={this.changeFirstNameHandler}
                    />
                  </div>
                  <div className={"form-group"}>
                    <label> Last Name: </label>
                    <input
                      className={"form-control"}
                      placeholder={"Last Name"}
                      name={"lastname"}
                      value={this.state.lastname}
                      onChange={this.changeLastNameHandler}
                    />
                  </div>
                  <div className={"form-group"}>
                    <label> Username: </label>
                    <input
                      className={"form-control"}
                      placeholder={"username"}
                      name={"username"}
                      value={this.state.username}
                      onChange={this.changeUserNameHandler}
                    />
                  </div>
                  <div className={"form-group"}>
                    <label> Password: </label>
                    <input
                      className={"form-control"}
                      placeholder={"password"}
                      name={"password"}
                      value={this.state.password}
                      onChange={this.changePasswordHandler}
                    />
                  </div>

                  <button type="submit" className={"btn btn-success"}>
                    {this.getTitle()}
                  </button>
                  <button
                    className={"btn btn-primary"}
                    onClick={this.cancel}
                    style={{ marginLeft: "10px" }}
                  >
                    Cancel
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default RegisterUser;
