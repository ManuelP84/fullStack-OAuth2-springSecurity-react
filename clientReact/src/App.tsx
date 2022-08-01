import { useState } from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import  Login  from "./pages/Login";
import  Home  from "./pages/Home";
import  Redirect  from "./pages/Redirect";
 
function App() {

  return (

    <div className='App'>   
    <BrowserRouter>
    <Routes>
      <Route path='/home' element={<Home/>}/>
      <Route path='/login' element={<Login/>}/>
      <Route path='/redirect' element={<Redirect/>}/>
      <Route path="/authorized" element={<Redirect/>} />
      
      
    </Routes>
    </BrowserRouter>
    </div>
  )
}

export default App
