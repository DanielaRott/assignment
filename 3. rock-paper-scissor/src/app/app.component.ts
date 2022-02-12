import { Component } from '@angular/core';

enum GameSelectionTypeEnum {
  ROCK = "ROCK",
  PAPER = "PAPER",
  SCISSOR = "SCISSOR"
}

enum GameStatusEnum {
  WIN = "WIN",
  LOSE = "LOSE",
  EVEN = "EVEN"
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent{
  public userWins: number = 0;
  public computerWins: number = 0;

  public userSelected: string;
  public computerSelected: string;

  public gameSelectionTypeEnum = GameSelectionTypeEnum;
  public gameStatusEnum = GameStatusEnum;

  public gameStatus: string = undefined;

  public winningCombinationList: string[] = [
    GameSelectionTypeEnum.ROCK + GameSelectionTypeEnum.SCISSOR,
    GameSelectionTypeEnum.PAPER + GameSelectionTypeEnum.ROCK,
    GameSelectionTypeEnum.SCISSOR + GameSelectionTypeEnum.PAPER,
  ]

  public loserCombinationList: string[] = [
    GameSelectionTypeEnum.ROCK + GameSelectionTypeEnum.PAPER,
    GameSelectionTypeEnum.SCISSOR + GameSelectionTypeEnum.ROCK,
    GameSelectionTypeEnum.PAPER + GameSelectionTypeEnum.SCISSOR,
  ]

  public onSelectOption(userOption: string): void {
    this.userSelected = userOption;

    const generateFactor = Math.floor(Math.random() * 3);
    this.computerSelected = [
      GameSelectionTypeEnum.ROCK,
      GameSelectionTypeEnum.PAPER,
      GameSelectionTypeEnum.SCISSOR
    ][generateFactor];

    this.handleResult();
  }

  public handleResult() {
    const gameCombination = this.userSelected + this.computerSelected;

    if(this.winningCombinationList.indexOf(gameCombination) > -1) {
      this.gameStatus = GameStatusEnum.WIN;
      this.userWins++;
    } else if(this.loserCombinationList.indexOf(gameCombination) > -1) {
      this.gameStatus = GameStatusEnum.LOSE;
      this.computerWins++;
    } else {
      this.gameStatus = GameStatusEnum.EVEN;
    }
  }
}
