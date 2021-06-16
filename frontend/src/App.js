import React from "react";
import "./App.css";

import { BrowserRouter as Router, Route } from "react-router-dom";
import ViewGameDetails from "./components/ViewGameDetails/ViewGameDetails";
import DisplayGameThumbnail from "./components/displayGame/DisplayGameThumbnail";
import Header from "./components/header/Header";
import Footer from "./components/footer/Footer";
import AddGame from "./components/addGame/AddGame";
import ListProducts from "./components/productsTable/ListProducts";
import ListUsers from "./components/usersTable/ListUsers";
import Contact from "./components/contact/Contact";
import RegisterUser from "./components/registerUser/RegisterUser";
import ViewUserDetails from "./components/ViewUserDetails/ViewUserDetails";

function App() {
  return (
    <div>
      <Router>
        <div className={"container head"}>
          <Header />
          <div className="container games">
            <Route path="/Home" exact component={DisplayGameThumbnail} />
            <Route path="/list-games" component={ListProducts} />
            <Route path="/list-users" component={ListUsers} />
            <Route path="/add-game/:id" component={AddGame} />
            <Route path="/view-game/:id" component={ViewGameDetails} />
            <Route path="/view-user/:id" component={ViewUserDetails} />
            <Route path="/contact" component={Contact} />
            <Route path="/register/:id" component={RegisterUser} />
          </div>
          <Footer />
        </div>
      </Router>
    </div>
  );
}

export default App;
