describe('Login', () => {
  it('should successfully log in with valid credentials', () => {
    //Login
    cy.visit('/'); 
    cy.findByLabelText(/Email/i).type('joao.silva@example.com');
    cy.findByLabelText(/Password/i).type('myPassword');
    cy.findByRole('button' , {name:/Login/i}).click();
    cy.url().should('include', '/Main'); 
    
  });
});