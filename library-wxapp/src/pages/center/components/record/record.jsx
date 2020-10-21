import React, { Component } from 'react'
import Taro from '@tarojs/taro'
import { View, Text } from '@tarojs/components'
import { AtButton } from 'taro-ui'

import "taro-ui/dist/style/components/button.scss" // 按需引入
import "taro-ui/dist/style/components/pagination.scss"
import './record.less'
import {HTTP_STATUS} from "../../../../servers/config";
import {getSeatRecord_servers} from "../../../../servers/servers";


export default class Record extends Component {

  constructor() {
    super(...arguments);
    this.state = {
      data: []
    }
  }

  componentWillMount () { }

  componentDidMount () {
    getSeatRecord_servers().then(res => {
      if (res.code === HTTP_STATUS.SUCCESS) {
        let data = JSON.parse(res.data)
        this.setState({
          data: data
        })
        //console.log(data)
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
      <View className='record'>
        <View className='title'>
          <Text>使用记录</Text>
        </View>
        <View>
          {this.state.data.map((item, index) => {
            return (
              <View key={index} className='list-item'>
                <Text className='time'>学习了 {item.time} 分钟</Text>
                <Text className='name'>{item.name}</Text>
                <Text className='date'>{item.date}</Text>
              </View>
            )
          })}
        </View>
        <View className='divider'>
          <AtButton
            type='secondary'
            size='small'
            circle
            onClick={() => (this.props.onWinChange('record', false))}
          >返回</AtButton>
        </View>

      </View>
    )
  }
}
