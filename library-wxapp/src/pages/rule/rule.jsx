import React, { Component } from 'react'
import { View, Text } from '@tarojs/components'
import { AtCard } from 'taro-ui'

import "taro-ui/dist/style/components/card.scss" // 按需引入
import './rule.less'

export default class Rule extends Component {

  constructor() {
    super(...arguments);
    this.state = {
      schoolName: '天津大学',
      status: '正常使用中',
      statusStyle: {color: 'green'}
    }
  }

  componentWillMount () { }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    return (
      <View className='rule'>
        <View className='title'>
          <Text className>{this.state.schoolName} 使用规则</Text>
        </View>

        <AtCard
          title='当前状态'
          extra={this.state.status}
          isFull
          extraStyle={this.state.statusStyle}
        >
          <Text>如果您的状态为异常，请联系馆内工作人员</Text>
        </AtCard>
        <AtCard
          title='用馆规则'
          isFull
        >
          <View>
            <Text>来馆选座、到馆签到、离馆退座，临时离开请点击暂离。</Text>
          </View>
          <View>
            <Text>系统开闭时间见对应馆室名下方小字。</Text>
          </View>
        </AtCard>
        <AtCard
          title='预约规则'
          isFull
        >
          <Text>开馆前两小时可预约当天座位</Text>
        </AtCard>
        <AtCard
          title='违规规则'
          isFull
        >
          <View>
            <Text>1.预约后规定时间内未到馆签到记录违规。</Text>
          </View>
          <View>
            <Text>2.被监督占座不签到记录违规。</Text>
          </View>
          <View>
            <Text>3.离馆不退座记录违规。</Text>
          </View>
        </AtCard>
        <AtCard
          title='黑名单规则'
          isFull
        >
          <View>
            <Text>1. 15天内发生3次违规进入黑名单，黑名单禁用系统7天。</Text>
          </View>
          <View>
            <Text>2. 违规使用监督占座功能的，查证后立即进入长期黑名单。</Text>
          </View>
          <View>
            <Text>3. 绑定他人学号或者借学号给他人绑定,查证后立即进入长期黑名单。</Text>
          </View>
        </AtCard>
      </View>
    )
  }
}
