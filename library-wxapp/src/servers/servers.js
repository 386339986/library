/* eslint-disable import/prefer-default-export */
import HttpRequest from "./http"

export const getResultData_servers = (postData) => {
  return HttpRequest.post('/api/white-screen/search', postData)
}

export const login_servers = (postData) => {
  return HttpRequest.post('/login', postData, "application/x-www-form-urlencoded")
}
