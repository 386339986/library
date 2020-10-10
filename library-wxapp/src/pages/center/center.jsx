import React, { Component } from 'react'
import Taro, { getCurrentInstance } from '@tarojs/taro'
import { View } from '@tarojs/components'


import WxInfo from "../../components/wxInfo";
import Detail from "./components/detail/detail";
import Index from "./components/index";
import Setting from "./components/setting/setting";
import Feedback from "./components/feedback/feedback";
import Message from "./components/message/message";
import Point from "./components/point/point";
import Mistake from "./components/mistake/mistake";
import './center.less'


export default class Center extends Component {

  constructor() {
    super(...arguments);
    this.state = {
      show: true,
      settingShow: false,
      feedbackShow: false,
      messageShow: false,
      pointShow: false,
      mistakeShow: false
    }
  }

  onWinChange (type, value) {
    switch (type) {
      case 'setting':
        this.setState({
          settingShow: value,
          show: !value
        })
        break;
      case 'feedback':
        this.setState({
          feedbackShow: value,
          show: !value
        })
        break;
      case 'message':
        this.setState({
          messageShow: value,
          show: !value
        })
        break;
      case 'point':
        this.setState({
          pointShow: value,
          show: !value
        })
        break;
      case 'mistake':
        this.setState({
          mistakeShow: value,
          show: !value
        })
      default:
        this.setState({
          show: true,
          settingShow: false,
          feedbackShow: false,
          messageShow: false,
          pointShow: false
        })
        break;
    }
  }

  componentWillMount () {
  }

  componentDidMount () {
    const params = getCurrentInstance().router.params
    if (params.hasOwnProperty('page')) {
      if (params['page'] === 'mistake') {
        this.setState({
          mistakeShow: true,
          show: false
        })
      }
    }
    console.log(this.state.show)
  }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    return (
      <View className='center'>
        <View className='top'>
          <WxInfo />
          <View className={(this.state.show ? '' : 'hide')}>
            <Detail />
          </View>
        </View>
        <View className={(this.state.show ? '' : 'hide')}>
          <Index onWinChange={this.onWinChange.bind(this)} />
        </View>
        <View className={(this.state.pointShow ? '' : 'hide')}>
          <Point onWinChange={this.onWinChange.bind(this)} />
        </View>
        <View className={(this.state.messageShow ? '' : 'hide')}>
          <Message onWinChange={this.onWinChange.bind(this)} />
        </View>
        <View className={(this.state.feedbackShow ? '' : 'hide')}>
          <Feedback onWinChange={this.onWinChange.bind(this)} />
        </View>
        <View className={(this.state.settingShow ? '' : 'hide')}>
          <Setting onWinChange={this.onWinChange.bind(this)} />
        </View>
        <View className={(this.state.mistakeShow ? '' : 'hide')}>
          <Mistake onWinChange={this.onWinChange.bind(this)} />
        </View>
      </View>
    )
  }
}
