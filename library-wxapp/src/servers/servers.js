/* eslint-disable import/prefer-default-export */
import HttpRequest from "./http"

export const getResultData_servers = (postData) => {
  return HttpRequest.post('/api/white-screen/search', postData)
}

export const login_servers = (postData) => {
  return HttpRequest.post('/login', postData, "application/x-www-form-urlencoded")
}

export const getListSchool_servers = () => {
  return HttpRequest.get('/school/list')
}

export const getUser_servers = (data) => {
  return HttpRequest.get('/student/info', data)
}

export const getRoomList_servers = (data) => {
  return HttpRequest.get('/room/list?schoolId=' + data)
}

export const getRoomInfo_servers = (data) => {
  return HttpRequest.get('/room/one?roomId=' + data)
}
