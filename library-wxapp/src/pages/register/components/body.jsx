import React, { Component } from 'react'
import Taro from '@tarojs/taro'
import { View, Text, Picker } from '@tarojs/components'
import {AtForm, AtButton, AtList, AtListItem, AtInput, AtMessage} from 'taro-ui'

import "taro-ui/dist/style/components/message.scss";
import "taro-ui/dist/style/components/form.scss";
import "taro-ui/dist/style/components/input.scss";
import "taro-ui/dist/style/components/list.scss";
import "taro-ui/dist/style/components/button.scss" // 按需引入
import './body.less'
import {getListSchool_servers, getUser_servers, login_servers} from "../../../servers/servers";
import { HTTP_STATUS } from '../../../servers/config'
import {connect} from "react-redux";
import {setName, setNumber, setPhone, setToken, setUserId, setSchool, setSchoolId} from "../../../actions/info";

@connect(({ localUserInfo }) => ({
  localUserInfo
}), (dispatch) => ({
  setName (name) {
    dispatch(setName(name))
  },
  setNumber (number) {
    dispatch(setNumber(number))
  },
  setPhone (phone) {
    dispatch(setPhone(phone))
  },
  setToken (token) {
    dispatch(setToken(token))
  },
  setUserId (id) {
    dispatch(setUserId(id))
  },
  setSchool (school) {
    dispatch(setSchool(school))
  },
  setSchoolId (id) {
    dispatch(setSchoolId(id))
  }
}))

export default class Body extends Component {

  constructor() {
    super(...arguments);
    this.state = {
      selector: [],
      schoolList: {},
      selectorChecked: '',
      schoolId: {},
      value: [0, 0],
      username: '',
      password: '',
      errorText: '错误',
      authorize: false
    }
  }

  listChange (event) {
    const column = event.detail.column;
    const value = event.detail.value;
    if (column === 0) {
      this.setState({
        selector: [this.state.selector[0], this.state.schoolList[this.state.selector[0][value]]],
        // selectorChecked: this.state.schoolList[this.state.selector[0][value]][0],
        value: [value, 0],
      })
    }
  }

  schoolChange (event) {
    this.setState({
      selectorChecked: this.state.selector[1][event.detail.value[1]]
    })
  }

  usernameChange (value) {
    this.setState({
      username: value
    })
    return value; // 微信小程序中必须return value才能改变值
  }

  passwordChange (value) {
    this.setState({
      password: value
    })
    return value; // 微信小程序中必须return value才能改变值
  }

  componentWillMount () { }

  componentDidMount () {
    if (Taro.getEnv() === Taro.ENV_TYPE.WEAPP) {
      Taro.getSetting({
        success: function (res) {
          if (res.authSetting['scope.userInfo']) {
            // console.log("用户已授权")
            this.setState({
              authorize: true
            })
          }
        }.bind(this)
      })
    }

    getListSchool_servers().then(res => {
      console.log(res)
      let schools = JSON.parse(res.data)
      let cityList = []
      let schoolList = {}
      let schoolId = {}
      for (let i in schools) {
        if (cityList.indexOf(schools[i].city) === -1) {
          cityList.push(schools[i].city)
        }
        if (!schoolList.hasOwnProperty(schools[i].city)) {
          schoolList[schools[i].city] = []
        }
        if (schoolList[schools[i].city].indexOf(schools[i].name) === -1) {
          schoolList[schools[i].city].push(schools[i].name)
          schoolId[schools[i].name] = schools[i].id
        }
      }
      let selector = [cityList, schoolList[cityList[0]]]
      this.setState({
        selector: selector,
        schoolList: schoolList,
        schoolId: schoolId,
        value: [0, 0]
      })
    }).catch(res => {
      console.log('getListSchool_servers error')
      console.log(res)
    })
  }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  getUserInfo() {
    getUser_servers().then(res => {
      console.log(res)
      if (res.code === HTTP_STATUS.SUCCESS) {
        let { id, schoolId, schoolName, userName, phone, idNumber } = res.data
        this.props.setUserId(id)
        this.props.setName(userName)
        this.props.setNumber(idNumber)
        this.props.setSchoolId(schoolId)
        this.props.setSchool(schoolName)
        this.props.setPhone(phone)
        Taro.switchTab({url: '/pages/index/index'});
      } else {
        Taro.atMessage({
          'message': '获取信息错误',
          'type': 'warning',
        });
      }
      //console.log(res)
    }).catch(err => {
      console.log(err)
      Taro.atMessage({
        'message': '发生错误',
        'type': 'error',
      });
    })
  }

  onSubmit (event) {
    // console.log(this.props.userInfo);
    if (this.state.selectorChecked.trim() === '') {
      Taro.atMessage({
        'message': '请选择学校',
        'type': 'warning',
      });
    } else if (this.state.username.trim() === '') {
      Taro.atMessage({
        'message': '请输入学号',
        'type': 'warning',
      });
    } else if (!(/^(?=.*[0-9a-zA-Z]).{5,20}$/).test(this.state.password)) {
      // (/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$/)
      Taro.atMessage({
        'message': '请输入包含数字和大小写字母的5~20位密码',
        'type': 'warning',
      });
    } else {
      let schoolId = this.state.schoolId[this.state.selectorChecked]
      let { username, password } = this.state
      // 测试使用 直接点学号登录
      // schoolId = 1
      // username = "2019229010"
      // password = "admin"
      login_servers({username: schoolId + ':' + username.trim(), password: password.trim()}).then(res => {
        // console.log(res)
        if (res.code === HTTP_STATUS.SUCCESS) {
          this.props.setToken(res.data)
          Taro.setStorageSync('Token',res.data)
          this.getUserInfo()
        } else {
          Taro.atMessage({
            'message': '登录失败,学号或密码错误',
            'type': 'error',
          });
        }
      }).catch(err => {
        console.log(err)
        Taro.atMessage({
          'message': '发生错误',
          'type': 'error',
        });
      })
    }
  }

  render () {
    return (
      <View className='body'>
        <AtMessage />
        <View>
          <AtForm className='form'>
            <Picker
              mode='multiSelector'
              range={this.state.selector}
              value={this.state.value}
              onChange={this.schoolChange.bind(this)}
              onColumnChange={this.listChange.bind(this)}
            >
              <AtList>
                <AtListItem
                  title='点击选择学校'
                  extraText={this.state.selectorChecked}
                />
              </AtList>
            </Picker>
            <AtInput
              name='username'
              title='学号'
              required
              placeholder='请输入学号'
              value={this.state.username}
              onChange={this.usernameChange.bind(this)}
            />
            <AtInput
              name='password'
              type='password'
              title='密码'
              required
              placeholder='请输入密码(5~20位)'
              maxlength='20'
              value={this.state.password}
              onChange={this.passwordChange.bind(this)}
            />
            <AtButton onClick={this.onSubmit.bind(this)} type='primary'>学号登录</AtButton>
          </AtForm>
        </View>
        {/*<View className='wx-button'>*/}
        {/*  <AtButton type='primary'>微信号快捷登录</AtButton>*/}
        {/*</View>*/}
      </View>
    )
  }
}
