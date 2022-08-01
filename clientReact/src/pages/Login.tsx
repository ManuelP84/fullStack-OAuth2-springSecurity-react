import * as React from 'react';
import { Link } from 'react-router-dom';

interface ILoginProps {
}

const Login: React.FunctionComponent<ILoginProps> = (props) => {
  return<>
  <Link to={"/redirect"} >Login</Link>
  </> ;
};

export default Login;
