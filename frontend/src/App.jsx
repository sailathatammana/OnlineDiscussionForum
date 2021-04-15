// NPM Packages
import React, { useState } from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";

// Project files
import Auth from "./services/Auth";
import Navbar from "./components/layout/Navbar";
import AuthPage from "./components/auth/AuthPage";
import HomePage from "./components/home/HomePage";
import PostsPage from "./components/posts/PostsPage";
import ChatPage from "./components/chat/ChatPage";
import "./App.css";

export default function App() {
  // State
  const [loggedIn, setLoggedIn] = useState(Auth.isLoggedIn());

  // Constants
  Auth.bindLoggedInStateSetter(setLoggedIn);

  // Components
  const loggedInRouter = (
    <BrowserRouter>
      <Navbar onLogout={() => Auth.logout()} />

      <div className="container mt-5">
        <Switch>
          <Route path="/posts">
            <PostsPage />
          </Route>

          <Route path="/chat">
            <ChatPage />
          </Route>

          <Route path="/">
            <HomePage />
          </Route>
        </Switch>
      </div>
    </BrowserRouter>
  );

  return loggedIn ? loggedInRouter : <AuthPage />;
}
