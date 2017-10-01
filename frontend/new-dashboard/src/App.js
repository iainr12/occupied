import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import axios from 'axios';
import myData from './data.json';
import {
  Table,
  TableBody,
  TableHeader,
  TableHeaderColumn,
  TableRow,
  TableRowColumn,
} from 'material-ui/Table';
import ActionFavorite from 'material-ui/svg-icons/action/favorite';
import ActionFavoriteBorder from 'material-ui/svg-icons/action/favorite-border';
import Checkbox from 'material-ui/Checkbox';

class Dashboard extends Component {

  constructor() {
    super();

    const tableHeaders = ['Floor', 'MALE East', 'FEMALE East', 'FEMALE West', 'MALE West'];

    this.state = {
      data: myData,
      tableHeaders: tableHeaders,
      requiredData: []
    }

    this.processData();
    
  }

  processData() {
    const data = myData;
    const requiredData = [];
    for(let i=0;i<=6;i++){
      requiredData[i] = {
        floor: i
      };
      for(let x=0;x<data.length;x++){
        let currentItem = data[x];
        // console.log(currentItem);
        if(currentItem.floor === i.toString()){
          if(currentItem.zone === 'EAST' && currentItem.gender === 'MALE'){
            requiredData[i].eastMale = currentItem.sensors;
          }
          if(currentItem.zone === 'WEST' && currentItem.gender === 'MALE'){
            requiredData[i].westMale = currentItem.sensors;
          }
          if(currentItem.zone === 'EAST' && currentItem.gender === 'FEMALE'){
            requiredData[i].eastFemale = currentItem.sensors;
          }
          if(currentItem.zone === 'WEST' && currentItem.gender === 'FEMALE'){
            requiredData[i].westFemale = currentItem.sensors;
          }
        }
      }
    }
    this.state.requiredData = requiredData;
  }

  getData() {
    // axios
    //   .get('http://codepen.io/jobs.json')
    //   .then(result => {
    //       console.log(result);
    //       this.setState({data: myData});
    //       // this.setState({data: result.data});
    //   })
    //   .then(() => {
    //       console.log(this.state.data);
    //   })
    console.log(myData);
    this.setState({data: myData});
  }

  render() {
    return (
      <div>
        <button onClick={this.getData.bind(this)}>
          Get data
        </button>

        <Table>
          <TableHeader>
            <TableRow>
              {this.state.tableHeaders.map(item => 
              <TableHeaderColumn key={item}>{item}</TableHeaderColumn>)}
            </TableRow>
          </TableHeader>
          <TableBody>
            {this.state.requiredData.map(item =>
              <TableRow key={'tableRow-'+item.floor}>
                <TableRowColumn key={item.floor + item.floor}>{item.floor}</TableRowColumn>
                <TableRowColumn key={item.floor + '-eastMale'}>
                <div style={{ display: 'inline-flex'}}>
                  <Checkbox style={{width: '20px'}}
                      checkedIcon={<ActionFavorite />}
                      uncheckedIcon={<ActionFavoriteBorder/>}
                      checked={item.eastMale[0].occupiedState==='open'}
                      disabled={true}
                  />
                  <Checkbox style={{width: '20px'}}
                      checkedIcon={<ActionFavorite />}
                      uncheckedIcon={<ActionFavoriteBorder/>}
                      checked={item.eastMale[1].occupiedState==='open'}
                      disabled={true}
                  />
                  <Checkbox style={{width: '20px'}}
                      checkedIcon={<ActionFavorite />}
                      uncheckedIcon={<ActionFavoriteBorder/>}
                      checked={item.eastMale[2].occupiedState==='open'}
                      disabled={true}
                  />
                </div>
                </TableRowColumn>
                <TableRowColumn key={item.floor + '-eastFemale'}>
                  <div style={{ display: 'inline-flex'}}>
                    <Checkbox style={{width: '20px'}}
                        checkedIcon={<ActionFavorite />}
                        uncheckedIcon={<ActionFavoriteBorder/>}
                        checked={item.eastFemale[0].occupiedState==='open'}
                        disabled={true}
                    />
                    <Checkbox style={{width: '20px'}}
                        checkedIcon={<ActionFavorite />}
                        uncheckedIcon={<ActionFavoriteBorder/>}
                        checked={item.eastFemale[1].occupiedState==='open'}
                        disabled={true}
                    />
                    <Checkbox style={{width: '20px'}}
                        checkedIcon={<ActionFavorite />}
                        uncheckedIcon={<ActionFavoriteBorder/>}
                        checked={item.eastFemale[2].occupiedState==='open'}
                        disabled={true}
                    />
                  </div>
                </TableRowColumn>
                <TableRowColumn key={item.floor + '-westFemale'}>
                  <div style={{ display: 'inline-flex'}}>
                    <Checkbox style={{width: '20px'}}
                        checkedIcon={<ActionFavorite />}
                        uncheckedIcon={<ActionFavoriteBorder/>}
                        checked={item.westFemale[0].occupiedState==='open'}
                        disabled={true}
                    />
                    <Checkbox style={{width: '20px'}}
                        checkedIcon={<ActionFavorite />}
                        uncheckedIcon={<ActionFavoriteBorder/>}
                        checked={item.westFemale[1].occupiedState==='open'}
                        disabled={true}
                    />
                    <Checkbox style={{width: '20px'}}
                        checkedIcon={<ActionFavorite />}
                        uncheckedIcon={<ActionFavoriteBorder/>}
                        checked={item.westFemale[2].occupiedState==='open'}
                        disabled={true}
                    />
                  </div>
                </TableRowColumn>
                <TableRowColumn key={item.floor + '-westMale'}>
                  <div style={{ display: 'inline-flex'}}>
                    <Checkbox style={{width: '20px'}}
                        checkedIcon={<ActionFavorite />}
                        uncheckedIcon={<ActionFavoriteBorder/>}
                        checked={item.westMale[0].occupiedState==='open'}
                        disabled={true}
                    />
                    <Checkbox style={{width: '20px'}}
                        checkedIcon={<ActionFavorite />}
                        uncheckedIcon={<ActionFavoriteBorder/>}
                        checked={item.westMale[1].occupiedState==='open'}
                        disabled={true}
                    />
                    <Checkbox style={{width: '20px'}}
                        checkedIcon={<ActionFavorite />}
                        uncheckedIcon={<ActionFavoriteBorder/>}
                        checked={item.westMale[2].occupiedState==='open'}
                        disabled={true}
                    />
                  </div>
                </TableRowColumn>
              </TableRow>)}
          </TableBody>
        </Table>
      </div>
    );
  }
}

export default Dashboard;
