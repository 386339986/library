import React, { Component } from 'react'
import Taro from '@tarojs/taro'
import { View, Text } from '@tarojs/components'
import { AtButton } from 'taro-ui'

import "taro-ui/dist/style/components/button.scss" // 按需引入
import './mistake.less'
import { getViolationRecord_servers } from "../../../../servers/servers";
import {HTTP_STATUS} from "../../../../servers/config";

export default class Mistake extends Component {

  constructor() {
    super(...arguments);
    this.state = {
      data: []
    }
  }

  componentWillMount () { }

  componentDidMount () {
    getViolationRecord_servers().then(res => {
      if (res.code === HTTP_STATUS.SUCCESS) {
        let data = JSON.parse(res.data)
        this.setState({
          data: data
        })
        console.log(data)
      }
    }).catch(err => {
      console.log(err)
    })
  }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    return (
      <View className='mistake'>
        <View className='title'>
          <Text>违规记录</Text>
        </View>
        <View>
          {this.state.data.map((item, index) => {
            return (
              <View key={index} className='list-item'>
                <Text className='name'>{item.reason}</Text>
                <Text className='date'>{item.time}</Text>
              </View>
            )
          })}
        </View>
        <AtButton
          type='secondary'
          size='small'
          circle
          onClick={() => (this.props.onWinChange('mistake', false))}
        >返回</AtButton>
      </View>
    )
  }
}
