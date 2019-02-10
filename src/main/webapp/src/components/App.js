import React from 'react';
import Header from "./sub/Header";

export default ({ children }) => {
  return (
    <div>
      <Header/>
      {children}
    </div>
  );
};