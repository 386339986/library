import React, { Component } from 'react'
import Taro from '@tarojs/taro'
import { View, Text } from '@tarojs/components'
import { AtGrid, AtNoticebar } from 'taro-ui'

import "taro-ui/dist/style/components/grid.scss" // 按需引入
import "taro-ui/dist/style/components/icon.scss" //
import "taro-ui/dist/style/components/noticebar.scss" //
import './func.less'

export default class FuncGrid extends Component {

  componentWillMount () { }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    return (
      <View className='func'>
        <AtGrid
          className='grid'
          data={
          [
            {
              iconInfo: {size: 30, color: 'red', value: 'clock'},
              value: '明日预约'
            },
            {
              iconInfo: {size: 30, color: 'orange', value: 'alert-circle'},
              value: '监督占座'
            },
            {
              iconInfo: {size: 30, color: 'blue', value: 'bookmark'},
              value: '使用记录'
            },
            {
              iconInfo: {size: 30, color: 'green', value: 'shopping-bag'},
              value: '积分商城'
            },
            {
              iconInfo: {size: 30, color: "orchid", value: 'folder'},
              value: '道具包'
            },
            {
              iconInfo: {size: 30, color: "pink", value: 'tag'},
              value: '积分任务'
            },
            {
              iconInfo: {size: 30, color: "sienna", value: 'numbered-list'},
              value: '排行榜'
            },
            {
              iconInfo: {size: 30, color: "tan", value: 'blocked'},
              value: '违规记录'
            }
          ]
        }
          columnNum={4}
        />
        <AtNoticebar
          icon='volume-plus'
          marquee
        >
          这是通告栏
        </AtNoticebar>
      </View>
    )
  }
}
