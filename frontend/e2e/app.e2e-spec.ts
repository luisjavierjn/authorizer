import { AuthorizerPage } from './app.po';

describe('authorizer App', () => {
  let page: AuthorizerPage;

  beforeEach(() => {
    page = new AuthorizerPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
