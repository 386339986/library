import { SET_NAME, SET_NUMBER, SET_PHONE, SET_TOKEN, SET_SCHOOL } from '../constants/actions'

const USER_INFO_STATE = {
  student_name: '未设定',
  student_number: '',
  student_phone: '',
  student_school: '未设定学校',
  token: ''
}

export default function userInfo (state = USER_INFO_STATE, action) {
  switch (action.type) {
    case SET_NAME:
      return {
        ...state,
        student_name: action.name
      }
    case SET_NUMBER:
      return {
        ...state,
        student_number: action.number
      }
    case SET_PHONE:
      return {
        ...state,
        student_phone: action.phone
      }
    case SET_TOKEN:
      return {
        ...state,
        token: action.token
      }
    case SET_SCHOOL:
      return {
        ...state,
        student_school: action.school
      }
    default:
      return state
  }
}
