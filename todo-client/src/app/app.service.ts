import { Injectable } from '@angular/core';
import {Headers, Http} from '@angular/http';
import 'rxjs/add/operator/map';
import {Todo} from './todo';

@Injectable()
export class AppService {
  base_url = 'http://localhost:8080/';
  constructor(private http: Http) { }

  getAllTodo() {
    return this.http.get(this.base_url + 'api/todo').map(res => res.json());
  }

  save(model: Todo) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json')
    return this.http.post(this.base_url + 'api/todo', model, { headers: headers }).map(res => res.json());
  }

  delete(id: number) {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json')
    return this.http.delete(this.base_url + 'api/todo/' + id);
  }

}
