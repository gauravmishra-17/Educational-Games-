import React, { Component } from "react";
import { useContext } from 'react';
import AppContext from '../AppContext';

import UserService from "../services/UserService";
import "./loginUser.css";

class LoginUser extends React.PureComponent  {

  //static contextType = AppContext;
  constructor(props) {
    super(props);

    this.state = {
      username: "",
      usernameErr: "",
      password: "",
      passwordErr: "",
      jwtMessage: "",
      adminState:{},
    };
    //this.context=useContext(AppContext);
    this.changeUserNameHandler = this.changeUserNameHandler.bind(this);
    this.changePasswordHandler = this.changePasswordHandler.bind(this);
    this.LoginUser = this.LoginUser.bind(this);
    this.cancel = this.cancel.bind(this);
  }
  componentDidMount(){
    const value = this.context;
    this.setState({adminState: value});
    console.log(value);
   
}

  LoginUser = (e) => {
    e.preventDefault();
    let user = {
      username: this.state.username,
      password: this.state.password,
    };
    UserService.loginUser(user).then((res) => {
      console.log(res.data.message);
      if (res.status === 200 )
        {localStorage.setItem("JwtToken", res.data.jwtToken);
        const s = res.data.message;
        console.log("llll");
        console.log(this.state.adminState);
        console.log(this.context);
       if(s === 'true')
        {this.context.setAdminTrue();}
        else
        {this.context.setAdminFalse();}
        window.location.href = "/home";}
      else
        this.setState({
          jwtMessage: "Username already exists",
        });
    });
  };
  validateInputs = () => {
    let isError = false;

    const errors = {};

    if (this.state.username.length < 3) {
      isError = true;
      errors.usernameErr = "Username must be atleast 3 characters long";
    }
    if (this.state.jwtMessage.length > 0) {
      isError = true;
      errors.usernameErr = "Username already exists";
    }
    if (this.state.password.length < 6) {
      isError = true;
      errors.passwordErr = "Password must be atleast 6 characters long";
    }

    if (isError) {
      this.setState({
        ...this.state,
        ...errors,
      });
    }
    return isError;
  };

  changeUserNameHandler = (e) => {
    this.setState({ username: e.target.value });
  };
  changePasswordHandler = (e) => {
    this.setState({ password: e.target.value });
  };

  cancel() {
    this.props.history.push("/Home");
  }

  render() {
    const { username, usernameErr, password, passwordErr, jwtMessage} =
      this.state;
    return (
      <div>
        <div className={"container addForm"}>
          <div className={"row"}>
            <div className={"card col-md-6 offset-md-3 offset-md-3 "}>
              <h3 className={"text-center"}>Login </h3>
              <div className={"card-body"}>
                <form onSubmit={this.LoginUser}>
                  <div className={"form-group"}>
                    <label> Username: </label>
                    <input
                      className={"form-control"}
                      placeholder={"username"}
                      name={"username"}
                      value={this.state.username}
                      onChange={this.changeUserNameHandler}
                    />
                    <label className={"errorFields"}>
                      {jwtMessage.length > 0 ? this.state.usernameErr : ""}
                    </label>
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
                    <label className={"errorFields"}>
                      {password.length < 6 ? this.state.passwordErr : ""}
                    </label>
                  </div>
                  <button type="submit" className={"btn btn-success"}>
                    <h3 className={"text-center"}>Login</h3>
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
LoginUser.contextType = AppContext;

export default LoginUser;
