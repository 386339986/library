import React, { Component } from 'react'
import Taro from '@tarojs/taro'
import {Text, View} from '@tarojs/components'
import {AtButton, AtCard, AtDivider, AtMessage} from 'taro-ui'

import './seat.less'
import "taro-ui/dist/style/components/card.scss"
import "taro-ui/dist/style/components/divider.scss"
import "taro-ui/dist/style/components/flex.scss"
import "taro-ui/dist/style/components/message.scss"
import {HTTP_STATUS} from "../../../../servers/config";
import {
  getMySeat_servers,
  postSeatDown_servers,
  postSeatLeave_servers,
  postSeatTemp_servers
} from "../../../../servers/servers";

export default class Seat extends Component {

  constructor() {
    super(...arguments);
    this.state = {
      hasSeat: false,
      campus: '',
      seatName: '',
      roomName: '',
      seatStatus: 0,
      modelOpen: false,
      modelText: ''
    }
  }

  refreshMySeatInfo() {
    getMySeat_servers().then(res => {
      if (res.code === HTTP_STATUS.SUCCESS) {
        let data = JSON.parse(res.data)
        this.setState({
          hasSeat: true,
          campus: data.campus,
          seatName: data.seatName,
          roomName: data.roomName,
          seatStatus: data.status
        })
      } else {
        this.setState({
          hasSeat: false
        })
      }
    }).catch(err => {
      console.log(err)
    })
  }

  componentWillMount () { }

  componentDidMount () {
    this.refreshMySeatInfo()
  }

  componentWillUnmount () { }

  componentDidShow () {
  }

  componentDidHide () { }

  seatDown() {
    postSeatDown_servers().then(res => {
      console.log(res)
      if (res.code === HTTP_STATUS.SUCCESS) {
        Taro.atMessage({
          'message': '操作成功',
          'type': "success"
        })
      } else if (res.code === HTTP_STATUS.CLIENT_ERROR) {
        Taro.atMessage({
          'message': res.msg,
          'type': "error"
        })
      }
      this.refreshMySeatInfo()
    }).catch(err => {
      console.log(err)
    })
  }

  seatLeave() {
    postSeatLeave_servers().then(res => {
      console.log(res)
      if (res.code === HTTP_STATUS.SUCCESS) {
        Taro.atMessage({
          'message': '操作成功',
          'type': "success"
        })
      } else if (res.code === HTTP_STATUS.CLIENT_ERROR) {
        Taro.atMessage({
          'message': res.msg,
          'type': "error"
        })
      }
      this.refreshMySeatInfo()
    }).catch(err => {
      console.log(err)
    })
  }

  seatTempLeave() {
    postSeatTemp_servers().then(res => {
      console.log(res)
      if (res.code === HTTP_STATUS.SUCCESS) {
        Taro.atMessage({
          'message': '操作成功',
          'type': "success"
        })
      } else if (res.code === HTTP_STATUS.CLIENT_ERROR) {
        Taro.atMessage({
          'message': res.msg,
          'type': "error"
        })
      }
      this.refreshMySeatInfo()
    }).catch(err => {
      console.log(err)
    })
  }

  getStatusText() {
    let text = ''
    switch (this.state.seatStatus) {
      case 1:
        text = '已退座'
        break
      case 2:
        text = '预约'
        break
      case 3:
        text = '已入座'
        break
      case 4:
        text = '暂离'
        break
      default:
        text = '未知状态'
        break
    }
    return text
  }

  getBuuton() {
    switch (this.state.seatStatus) {
      case 2:
        return (
          <View className='at-row at-row__justify--around'>
            <View className='at-col at-col-2'>
              <AtButton size='small' type='primary' onClick={this.seatDown.bind(this)}>签到</AtButton>
            </View>
            <View
              className='at-col at-col-2'>
              <AtButton size='small' type='primary' onClick={this.seatLeave.bind(this)}>退座</AtButton>
            </View>
          </View>

        )
        break
      case 3:
        return (
          <View className='at-row at-row__justify--around'>
            <View className='at-col at-col-2'>
              <AtButton size='small' type='primary' onClick={this.seatTempLeave.bind(this)}>暂离</AtButton>
            </View>
            <View className='at-col at-col-2'>
              <AtButton size='small' type='primary' onClick={this.seatLeave.bind(this)}>退座</AtButton>
            </View>
          </View>
        )
        break
      case 4:
        return (
          <View className='at-row at-row__justify--around'>
            <View className='at-col at-col-2'>
              <AtButton size='small' type='primary' onClick={this.seatDown.bind(this)}>入座</AtButton>
            </View>
            <View className='at-col at-col-2'>
              <AtButton size='small' type='primary' onClick={this.seatDown.bind(this)}>退座</AtButton>
            </View>
          </View>
        )
        break
      default:
        break
    }
  }

  showSeatCard() {
    if (this.state.hasSeat) {
      return (
        <AtCard
          title='我的座位'
          extra={this.getStatusText()}
        >
          <View>
            <View>
              <View className='seat-name'>
                <Text>{this.state.campus} {this.state.roomName}室 {this.state.seatName}座</Text>
              </View>
              <View>
                <Text></Text>
              </View>
            </View>
            <AtDivider lineColor='#ff9900' />
            {this.getBuuton()}
          </View>
        </AtCard>
      )
    }
  }

  render () {
    return (
      <View className='seat-info'>
        <AtMessage />
        {this.showSeatCard()}
      </View>
    )
  }
}
