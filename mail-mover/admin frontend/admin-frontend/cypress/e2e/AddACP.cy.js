describe('Login', () => {
  it('should successfully log in with valid credentials', () => {
    //Login
    cy.visit('/'); 
    cy.findByLabelText(/Username/i).type('admin');
    cy.findByLabelText(/Password/i).type('admin');
    cy.findByRole('button' , {name:/Login/i}).click();
    cy.url().should('include', '/Main'); 
    
    //Add acp and test button desabled/enabled
    cy.findByRole('tab' , {name:/ACP POINTS/i}).click();   
    cy.findByRole('button' , {name:/add acp/i}).click();
    cy.get('.acpname').type("fakeName");
    cy.findByRole('button' , {name:/add acp/i}).should('be.disabled');
    cy.get('.address').type("fakeAddress");
    cy.findByRole('button' , {name:/add acp/i}).should('be.disabled');
    cy.get('.email').type("fake@email.com");
    cy.findByRole('button' , {name:/add acp/i}).should('be.disabled');
    cy.get('.password').type("fakePassword");
    let numberofAcps;
    cy.get(".acprow").its('length').then((number) => {
      numberofAcps = number;
      cy.findByRole('button' , {name:/add acp/i}).should('be.enabled').click();
      //cy.get('.acprow').its('length').should('be.greaterThan' , numberofAcps);
    });
    
    //Test Filters and Reset button
    cy.findByRole('tab' , {name:/PARCELS/i}).click();   
    cy.get('.filteracp').type("1");
    cy.get('.btn-flt-acp').click();
   // cy.get(".orderrow").should('not.exist');
    cy.get('.filteracp').type("2345");
    cy.get('.btn-flt-acp').click();
   // cy.get(".orderrow").should('exist');
    cy.get('.filteracp').type("1");
    cy.get('.btn-flt-acp').click();
    //cy.get(".orderrow").should('not.exist');
    cy.findByRole('button' , {name:/RESET FILTERS/i}).click(); 
    //cy.get(".orderrow").should('exist');
    cy.get('.filterid').type("1");
    cy.get('.btn-flt-id').click();
    //cy.get(".orderrow").its('length').should('eql' ,1);
  });
});