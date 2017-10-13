import {Component} from '@angular/core';
import {AppService} from './app.service';
import {Todo} from './todo';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [AppService]
})
export class AppComponent {

  allTodo = [];
  model: Todo;
  public hasError: boolean;
  public showErrorMessage: boolean;

  constructor(private _httpService: AppService) {
    this.model = new Todo();
    this.model.status = 'PENDING'
    this.loadTodo();
    this.hasError = true;
    this.showErrorMessage = false;
  }

  public loadTodo() {
    this._httpService.getAllTodo()
      .subscribe((res) => this.allTodo = res, (error) => console.log(error), () => console.log('Completed Load Todo'));
  }

  public createTodo(model: Todo) {
    if (!this.hasError) {
      this._httpService.save(model).subscribe((res) => {
        this.loadTodo();
        this.model.description = '';
        this.hasError = true;
      });
    }
  }

  public valuechange(value) {
    console.log(value)
    if (this.model.description === '') {
      this.hasError = true;
    } else {
      this.hasError = false;
    }

    if (this.hasError) {
      this.showErrorMessage = true;
    } else {
      this.showErrorMessage = false;
    }
  }

  public updateStatus(todo: Todo) {
    todo.status = 'COMPLETED';
    this._httpService.save(todo).subscribe();
  }

  public delete(todo: Todo) {
    this._httpService.delete(todo.id).subscribe((res) => {
      this.loadTodo();
    });
  }
}
