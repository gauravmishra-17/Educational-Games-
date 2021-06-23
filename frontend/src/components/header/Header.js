import React, { useState, useEffect, Component } from "react";
import "./header.css";
import { CSSTransition } from "react-transition-group";

export default function Header({ history, location,props }) {
  const [isNavVisible, setNavVisibility] = useState(false);
  const [isSmallScreen, setIsSmallScreen] = useState(false);
  const [isLoggedin, setIsLoggedIn] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);

  useEffect(() => {
    const mediaQuery = window.matchMedia("(max-width: 700px)");
    mediaQuery.addListener(handleMediaQueryChange);
    handleMediaQueryChange(mediaQuery);
    authCheck();
    isAdminCheck();
    return () => {
      mediaQuery.removeListener(handleMediaQueryChange);
    };
  }, [isLoggedin,isAdmin]);

  const handleMediaQueryChange = (mediaQuery) => {
    if (mediaQuery.matches) {
      setIsSmallScreen(true);
    } else {
      setIsSmallScreen(false);
    }
  };

  const toggleNav = () => {
    setNavVisibility(!isNavVisible);
  };

  const authCheck = () => {
    if (localStorage.getItem("JwtToken") !== null) {
      setIsLoggedIn(true);
    }
    
  };
  const isAdminCheck=() =>
  {
   if (this.props.isAdmin === "true")
   setIsAdmin(true);
   else
   setIsAdmin(false);

  }

  const logout = () => {
    localStorage.removeItem("JwtToken");
    setIsLoggedIn(false);
    window.location.href = "/home";
  };

  return (
    <header className="Header">
      <div className={"myLogo"}></div>
      <CSSTransition
        in={!isSmallScreen || isNavVisible}
        timeout={350}
        classNames="NavAnimation"
        unmountOnExit
      >
        {!isLoggedin ? (
          <nav className="Nav">
            <a href="/home">Home</a>
            <a href="/register/register-user">Register</a>
            <a href="/login">Login</a>
            <a href="/contact">Contact</a>
          </nav>
        ) : (


          <nav className="Nav">
            <a href="/home">Home</a>
            <a href="/list-games">Add new Game</a>
            <a href="/list-users">Add new User</a>
            <button onClick={logout} href="/home">
              Logout
            </button>
            <a href="/contact">Contact</a>
          </nav>


        )}
      </CSSTransition>
      <button onClick={toggleNav} className="Burger">
        🍔
      </button>
    </header>
  );
}
