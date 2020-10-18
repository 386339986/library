import React, { Component } from 'react'
import Taro from '@tarojs/taro';
import {View} from '@tarojs/components'
import { connect } from "react-redux";

import "taro-ui/dist/style/components/button.scss" // 按需引入
import './index.less'
import Top from "./components/top/top";
import FuncGrid from "./components/func/func";
import List from "./components/list/list";
import { setName, setNumber, setPhone, setToken } from "../../actions/info";
import { login_servers } from "../../servers/servers";

@connect(({ userInfo }) => ({
  userInfo
}), (dispatch) => ({
  setName () {
    dispatch(setName('LKNIGHT'))
  },
  setNumber (number) {
    dispatch(setNumber(number))
  },
  setPhone (phone) {
    dispatch(setPhone(phone))
  },
  setToken (token) {
    dispatch(setToken(token))
  }
}))

export default class Select extends Component {

  componentWillMount () { }

  componentDidMount () {
    let token = this.props.userInfo.token
    if (token === null || token === '') {
      Taro.redirectTo({
        url: '/pages/register/register'
      })
    }

    // console.log(this.props)
    // login_servers({username: "2019229010", password: "admin"}).then(res => {
    //   console.log(res)
    // }).catch(err => {
    //   console.log(err)
    // })
  }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    return (
      <View className='select'>
        <Top />
        <FuncGrid />
        <List />
      </View>
    )
  }
}
