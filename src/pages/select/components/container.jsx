import React, { Component } from 'react'
import { View } from '@tarojs/components'
import AnyTouch from 'any-touch';

import "taro-ui/dist/style/components/button.scss" // 按需引入
import './container.less'


export default class Container extends Component {

  constructor(props) {
    super(props);
    this.state = {
      gridCellSize: 35,
      gridSize: {row: 3, col: 5},
      grid: [
        [4, 2, 3, 0, 4],
        [4, 2, 3, 0, 5],
        [0, 0, 0, 1, 0]
      ],
      seatNumber: {r0: {c0: '101', c2: '103', c4: '105'}, r1: {c0: '102', c2: '104', c4: '106'}},
      scale: 1,
      top: '0PX',
      left: '0PX'
    }
    this.at = new AnyTouch ()
  }

  componentWillMount () {
    // console.log(this.at)
    this.at.set({domEvents: false})
    this.at.on('pinch', (ev) => this.onPinch(ev))
    this.at.on('pan', (ev) => this.onPanmove(ev))
  }

  onPanmove(ev) {
    this.setState({
      top: parseInt(this.state.top) + ev.deltaY + 'PX',
      left: parseInt(this.state.left) + ev.deltaX + 'PX'
    })
  }

  onPinch(ev) {
    this.setState({
      scale: Math.round(this.state.scale * ev.deltaScale * 100) / 100
    })
  }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  getSeatNumber(item, rowIndex, colIndex) {
    let number = '';
    switch (item) {
      case 3:
      case 4:
      case 5:
        number = this.state.seatNumber['r' + rowIndex]['c' + colIndex];
        break;
      default:
        break;
    }
    return number
  }

  createGrid (item, rowIndex, colIndex) {

    return (
      item > 0 ?
      <View
        className={'grid-cell grid-' + item}
        key={rowIndex + ',' + colIndex}
        style={'top:' + (this.state.gridCellSize * rowIndex + 10) + 'PX;left:'
        + this.state.gridCellSize * colIndex + 'PX'}
      >
        <View
          className='img'
          hoverClass='none'
        >
          {this.getSeatNumber(item, rowIndex, colIndex)}
        </View>
      </View>
        :
        null
    )
  }

  render () {
    return (
      <View className='container-fluid'>
        <View
          className='wrapper'
          onTouchStart={this.at.catchEvent.bind(this.at)}
          onTouchMove={this.at.catchEvent.bind(this.at)}
          onTouchEnd={this.at.catchEvent.bind(this.at)}
          onTouchCancel={this.at.catchEvent.bind(this.at)}
        >
          <View
            className='content'
            style={'width:' + this.state.gridCellSize * this.state.gridSize['col']
              + 'PX;height:' + this.state.gridCellSize * this.state.gridSize['row'] + 'PX;'
              + 'transform: translate(' + this.state.left + ',' + this.state.top +') scale(' + this.state.scale + ');'}

          >
            {
              this.state.grid.map((rowItem, rowIndex) => (
                rowItem.map((colItem, colIndex) => (
                  this.createGrid(colItem, rowIndex, colIndex)
                ))
              ))
            }
          </View>
        </View>
      </View>
    )
  }
}
