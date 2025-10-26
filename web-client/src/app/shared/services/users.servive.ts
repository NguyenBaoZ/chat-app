import { Injectable } from '@angular/core';
import { RestService } from './rest.servive';

@Injectable({
    providedIn: 'root'
})
export class UsersService {

    constructor(private restService: RestService) {}

    public getContactsList(userId: string = 'U0001') {
        return this.restService.sendGetRequest(`user-service/api/users/${userId}/contacts`);
    }
}
