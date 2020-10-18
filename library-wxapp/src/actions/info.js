import { SET_NAME, SET_NUMBER, SET_PHONE, SET_TOKEN, SET_SCHOOL, SET_USER_ID } from '../constants/actions'

export const setName = (student_name) => {
  return {
    type: SET_NAME,
    name: student_name
  }
}

export const setNumber = (student_number) => {
  return {
    type: SET_NUMBER,
    number: student_number
  }
}

export const setPhone = (student_phone) => {
  return {
    type: SET_PHONE,
    phone: student_phone
  }
}

export const setToken = (student_token) => {
  return {
    type: SET_TOKEN,
    token: student_token
  }
}

export const setSchool = (student_school) => {
  return {
    type: SET_SCHOOL,
    school: student_school
  }
}

export const setUserId = (student_id) => {
  return {
    type: SET_USER_ID,
    id: student_id
  }
}
