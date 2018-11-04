import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.scss']
})
export class PageComponent implements OnInit {
  
  public pageState = PageState.LOGIN;

  constructor() { }

  ngOnInit() {
  }

  isPageState(state: string) {
    return this.pageState === PageState[state];
  }

  setPageState(state: string) {
    return this.pageState = PageState[state];
  }

}

enum PageState {
  LOGIN,
  SIGN_UP
}