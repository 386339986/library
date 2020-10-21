import { SET_NAME, SET_NUMBER, SET_PHONE, SET_TOKEN, SET_SCHOOL, SET_USER_ID, SET_SCHOOL_ID } from '../constants/actions'

const USER_INFO_STATE = {
  student_name: '未登录用户',
  student_number: '',
  student_phone: '',
  student_school: '',
  student_id: 0,
  school_id: 0,
  token: ''
}

export default function localUserInfo (state = USER_INFO_STATE, action) {
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
    case SET_USER_ID:
      return {
        ...state,
        student_id: action.id
      }
    case SET_SCHOOL_ID:
      return {
        ...state,
        school_id: action.school_id
      }
    default:
      return state
  }
}
