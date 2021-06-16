import React, { Component } from "react";
import UserService from "../services/UserService";
import "./viewGameDetails.css";

class ViewUserDetails extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: this.props.match.params.id,
      user: {},
    };
    this.cancel = this.cancel.bind(this);
    this.editUser = this.editUser.bind(this);
  }

  componentDidMount() {
    UserService.getUserById(this.state.id).then((res) => {
      this.setState({ user: res.data });
    });
  }

  cancel() {
    this.props.history.push("/list-users");
  }

  editUser(id) {
    this.props.history.push(`/register/${id}`);
  }

  render() {
    return (
      <div>
        <br />
        <div className={"card col-md-6 offset-md-3 card"}>
          <h3 className={"text-center"}> User Details </h3>
          <div className={"card-body"}>
            <div className={"row"}>
              <lable> Email: </lable>
              <div className={"info"}> {this.state.user.email}</div>
            </div>
            <div className={"row"}>
              <lable> First Name : </lable>
              <div className={"info"}> {this.state.user.firstname}</div>
            </div>
            <div className={"row"}>
              <lable> Last Name: </lable>
              <div className={"info"}> {this.state.user.lastname}</div>
            </div>
            <div className={"row"}>
              <lable> Username: </lable>
              <div className={"info"}> {this.state.user.username}</div>
            </div>
          </div>
        </div>
        <div className="col text-center button">
          <button
            onClick={() => this.editUser(this.state.user.id)}
            className={"btn btn-primary bt"}
          >
            Edit
          </button>
          <button
            className={"btn btn-primary bt"}
            onClick={this.cancel}
            style={{ marginLeft: "10px" }}
          >
            Back to List
          </button>
        </div>
      </div>
    );
  }
}

export default ViewUserDetails;
