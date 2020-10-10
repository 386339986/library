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

export default class Body extends Component {

  constructor() {
    super(...arguments);
    const city = ['北京市', '天津市', '广州市'];
    const schoolList = {'北京市': ['北京大学', '清华大学', '北京邮电大学'],
        '天津市': ['天津大学', '南开大学', '天津医科大学'],
        '广州市': ['中山大学', '华南理工大学', '广州大学']};
    this.state = {
      selector: [city, schoolList[city[0]]],
      schoolList: {'北京市': ['北京大学', '清华大学', '北京邮电大学'],
        '天津市': ['天津大学', '南开大学', '天津医科大学'],
        '广州市': ['中山大学', '华南理工大学', '广州大学']},
      selectorChecked: '',
      value: [0, 0],
      phone: '',
      password: '',
      errorText: '错误',
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
    // } else if (column === 1) {
    //   this.setState({
    //     selectorChecked: this.state.selector[1][value]
    //   });
    // }

  }

  schoolChange (event) {
    this.setState({
      selectorChecked: this.state.selector[1][event.detail.value[1]]
    })
  }

  phoneChange (value) {
    this.setState({
      phone: value
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

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  onSubmit (event) {
    console.log(this.state);
    if (this.state.selectorChecked.trim() === '') {
      Taro.atMessage({
        'message': '请选择学校',
        'type': 'error',
      });
    } else if (!(/^1\d{10}$/).test(this.state.phone)) {
      Taro.atMessage({
        'message': '请输入正确的11位数手机号码',
        'type': 'error',
      });
    } else if (!(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$/).test(this.state.password)) {
      Taro.atMessage({
        'message': '请输入包含数字和大小写字母的8~20位密码',
        'type': 'error',
      });
    } else {
      Taro.navigateTo({url: '/pages/index/index'});
    }
  }

  render () {
    return (
      <View className='body'>
        <AtMessage />
        <View>
          <AtForm
            onSubmit={this.onSubmit.bind(this)}
            className='form'
          >
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
              name='phone'
              type='digit'
              title='手机号'
              required
              placeholder='请输入手机号码'
              maxlength='11'
              value={this.state.phone}
              onChange={this.phoneChange.bind(this)}
            />
            <AtInput
              name='password'
              type='password'
              title='密码'
              required
              placeholder='请输入密码(8~20位)'
              maxlength='20'
              value={this.state.password}
              onChange={this.passwordChange.bind(this)}
            />
            <AtButton formType='submit' type='primary'>绑定微信号并登录</AtButton>
          </AtForm>
        </View>
      </View>
    )
  }
}
