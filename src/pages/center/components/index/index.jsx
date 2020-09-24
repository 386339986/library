import React, { Component } from 'react'
import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtList, AtListItem} from 'taro-ui'

import "taro-ui/dist/style/components/button.scss" // 按需引入
import "taro-ui/dist/style/components/icon.scss" // 按需引入
import './index.less'

export default class Index extends Component {

  constructor(props) {
    super(props);
    this.state = {
    }
  }

  componentWillMount () { }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    return (
      <View className='index-body'>
        <AtList>
          <AtListItem
            title='我的积分'
            arrow='right'
            extraText='0分'
            iconInfo={{ size: 24, color: '#ffa323', value: 'star', }}
            onClick={() => (this.props.onWinChange('point', true))}
          />
          <AtListItem
            title='消息中心'
            arrow='right'
            extraText='0条'
            iconInfo={{ size: 24, color: '#5ad745', value: 'mail', }}
            onClick={() => (this.props.onWinChange('message', true))}
          />
          <AtListItem
            title='意见反馈'
            arrow='right'
            iconInfo={{ size: 24, color: '#eeba38', value: 'message', }}
            onClick={() => (this.props.onWinChange('feedback', true))}
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
            onClick={() => (this.props.onWinChange('setting', true))}
          />
        </AtList>
      </View>
    )
  }
}
