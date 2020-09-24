import React, { Component } from 'react'
import Taro from '@tarojs/taro'
import {View, Text, Picker} from '@tarojs/components'
import {AtForm, AtButton, AtList, AtListItem, AtInput, AtMessage} from 'taro-ui'

import "taro-ui/dist/style/components/message.scss";
import "taro-ui/dist/style/components/form.scss";
import "taro-ui/dist/style/components/input.scss";
import "taro-ui/dist/style/components/list.scss";
import "taro-ui/dist/style/components/button.scss" // 按需引入
import './setting.less'

export default class Setting extends Component {

  constructor(props) {
    super(props);
    const city = ['北京市', '天津市', '广州市'];
    const schoolList = {'北京市': ['北京大学', '清华大学', '北京邮电大学'],
      '天津市': ['天津大学', '南开大学', '天津医科大学'],
      '广州市': ['中山大学', '华南理工大学', '广州大学']};
    this.state = {
      selector: [city, schoolList[city[0]]],
      schoolList: {'北京市': ['北京大学', '清华大学', '北京邮电大学'],
        '天津市': ['天津大学', '南开大学', '天津医科大学'],
        '广州市': ['中山大学', '华南理工大学', '广州大学']},
      selectorChecked: '天津大学',
      value: [0, 0],
      phone: '',
      password: '',
      errorText: '错误',
    }
  }

  componentWillMount () { }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

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

  render () {
    return (
      <View className='setting'>
        <AtForm
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
                title='当前绑定学校'
                extraText={this.state.selectorChecked}
              />
            </AtList>
          </Picker>
          <AtList>
            <AtListItem
              title='接受小纸条'
              isSwitch
              switchIsCheck
            >

            </AtListItem>
          </AtList>
          <AtButton formType='submit' type='primary' size='small' circle>修改</AtButton>
          <AtButton type='secondary' size='small' circle onClick={() => (this.props.onWinChange('setting', false))}>返回</AtButton>
        </AtForm>
      </View>
    )
  }
}
