import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { throwError, of } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Store } from '@ngrx/store';

@Injectable({
    providedIn: 'root'
})
export class RestService {
    private restApiServer = environment.apiUrl

    constructor(private httpClient: HttpClient, private store: Store) {

    }

    public sendGetRequest(urn: any, getParams = {} as any, externalConfig = {} as any, autoLogin = false, ignoreLogin = false as any) {
        const options = externalConfig;
        options.params = getParams;
        return this.httpClient.get<any>(this.restApiServer + urn, options)
    }

    public sendPostRequest(urn: any, body = {} as any, externalConfig = {} as any, isSkipFormatRequestBody = false as any, isFormData = false) {
        const options = externalConfig;
        return this.httpClient.post<any>(this.restApiServer + urn, body, options)
    }

    public sendPatchRequest(urn: any, body = {} as any, externalConfig = {} as any) {
        const options = externalConfig;
        return this.httpClient.patch<any>(this.restApiServer + urn, body, options)
    }

    public sendPutRequest(urn: any, body = {} as any, externalConfig = {} as any) {
        const options = externalConfig;
        return this.httpClient.put<any>(this.restApiServer + urn, body, options)
    }

    public sendDeleteRequest(urn: any, getParams = {} as any, externalConfig = {} as any) {
        const options = externalConfig;
        options.params = getParams;
        return this.httpClient.delete<any>(this.restApiServer + urn, options)
    }
}
