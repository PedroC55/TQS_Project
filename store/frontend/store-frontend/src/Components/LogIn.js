import '../css/login.css';
import profile from "../Assets/b.png";
import email from "../Assets/email.jpg";
import pass from "../Assets/pass.png";
import { Link } from 'react-router-dom';
import { useState } from 'react';
function LoginUi() {
  const [userText ,setUserText] = useState('');
  const [passText ,setPassText] = useState('');

  const handleUserChange = (event) => {
    setUserText(event.target.value);
  };

  const handlePassChange = (event) => {
    setPassText(event.target.value);
  };

  const checkUser = () => {
    if (userText == "user" && passText == "user") {
      window.location.href = '/app';
    }
  }
  return (
    <div className="main">
     <div className="sub-main">
       <div>
         <div className="imgs">
           <div className="container-image">
             <img src={profile} alt="profile" className="profile"/>

           </div>


         </div>
         <div>
           <h1>Login Page</h1>
           <div>
             <img src={email} alt="email" className="email"/>
             <input name='user' type="text" placeholder="User name" className="name" value={userText} onChange={handleUserChange}/>
           </div>
           <div className="second-input">
             <img src={pass} alt="pass" className="email"/>
             <input name='pass' type="password" placeholder="Password" className="name" value={passText} onChange={handlePassChange}/>
           </div>
          <div className="login-button">
          
            <button onClick={checkUser} name="btnLogin">Login</button>
          
          </div>
           
            <p className="link">
              <a href="#">Forgot password ?</a> Or<a href="#">Sign Up</a>
            </p>
           
 
         </div>
       </div>
       

     </div>
    </div>
  );
}

export default LoginUi;