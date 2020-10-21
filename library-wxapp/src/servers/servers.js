import HttpRequest from "./http"

export const login_servers = (postData) => {
  return HttpRequest.post('/user/login', postData, "application/x-www-form-urlencoded")
}

export const getListSchool_servers = () => {
  return HttpRequest.get('/school/list')
}

export const getUser_servers = () => {
  return HttpRequest.get('/student/info')
}

export const getRoomList_servers = (data) => {
  return HttpRequest.get('/room/list?schoolId=' + data)
}

export const getRoomInfo_servers = (data) => {
  return HttpRequest.get('/room/one?roomId=' + data)
}

// 学生正常选座接口
export const postSelectSeat_servers = (roomId, seat) => {
  return HttpRequest.post('/seat/select/' + roomId + '/' + seat[0] + '/' + seat[1])
}

// 获取学生当前座位
export const getMySeat_servers = () => {
  return HttpRequest.get('/seat/my/seat')
}

// 查看学生当前座位使用时长
export const getSeatTime_servers = () => {
  return HttpRequest.get('/seat/check/time')
}

// 入座
export const postSeatDown_servers = () => {
  return HttpRequest.post('/seat/down')
}

// 退座&退选
export const postSeatLeave_servers = () => {
  return HttpRequest.post('/seat/leave')
}

// 暂离
export const postSeatTemp_servers = () => {
  return HttpRequest.post('/seat/temp/leave')
}

// 获取使用记录
export const getSeatRecord_servers = () => {
  return HttpRequest.get('/seat/record')
}

// 获取违规记录
export const getViolationRecord_servers = () => {
  return HttpRequest.get('/violation/record')
}
