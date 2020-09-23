import React, { Component } from 'react'
import Taro from '@tarojs/taro'
import { View, Text } from '@tarojs/components'
import { AtList, AtListItem } from 'taro-ui'

import "taro-ui/dist/style/components/list.scss" // 按需引入
import "taro-ui/dist/style/components/icon.scss" // 按需引入
import WxInfo from "../../components/wxInfo";
import './center.less'



export default class Center extends Component {

  componentWillMount () { }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    return (
      <View className='center'>
        <View className='top'>
          <WxInfo />
          <View className='study-detail'>
            <View className='table-cell'>
              10小时
              <View className='info'>
                学习时间
              </View>
            </View>
            <View className='table-cell'>
              10000名
              <View className='info'>
                排名
              </View>
            </View>
            <View className='table-cell'>
              3小时
              <View className='info'>
                单日最长学习时间
              </View>
            </View>
            {/*<View className='bg' />*/}
          </View>
        </View>

        <View>
          <AtList>
            <AtListItem
              title='我的积分'
              arrow='right'
              extraText='0分'
              iconInfo={{ size: 24, color: '#ffa323', value: 'star', }}
            />
            <AtListItem
              title='消息中心'
              arrow='right'
              extraText='0条'
              iconInfo={{ size: 24, color: '#5ad745', value: 'mail', }}
            />
            <AtListItem
              title='意见反馈'
              arrow='right'
              iconInfo={{ size: 24, color: '#eeba38', value: 'message', }}
            />
            <AtListItem
              title='使用规则'
              arrow='right'
              iconInfo={{ size: 24, color: '#ff3030', value: 'alert-circle', }}
              onClick={() => (Taro.switchTab({ url: '/pages/rule/rule' }))}
            />
            <AtListItem
              title='个人设置'
              arrow='right'
              iconInfo={{ size: 24, color: '#3f7be5', value: 'settings', }}
            />
          </AtList>
        </View>
      </View>
    )
  }
}
