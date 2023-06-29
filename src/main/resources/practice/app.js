Vue.createApp({
  data() {
    return {
      count: 0,
      btActionText: 'Add',
      // change default result
      resultMessage: 'Result: ' + 0,
      consoleOut: '',
    };
  },
  watch: {
    count(value) {
      console.log(value);
      this.setResult(value);
      // To set === -30 | 30 in order to create bug
      if (this.count >= 30 || this.count <= -30) {
        this.logResult('Setting timer 5 sec to reset count');
        setTimeout(() => {
          this.count = 0;
        }, 5000);
      }
    },
  },

  methods: {
    logResult(value) {
      var today = new Date();
      var date =
        today.getFullYear() +
        '-' +
        (today.getMonth() + 1) +
        '-' +
        today.getDate() +
        ' : ' +
        today.getHours() +
        '-' +
        today.getMinutes() +
        '-' +
        today.getSeconds();
      this.consoleOut += `<div>${date} - ${value}</div>`;
    },
    setResult(value) {
      this.resultMessage = 'Result: ' + value;
    },
    updateBtnText(event) {
      const selectedOption = event.target.selectedOptions[0].value;
      switch (selectedOption) {
        case 'increase':
          this.logResult('Increase selected');
          console.log('increase selected');
          this.btActionText = 'Add';
          break;
        case 'decrease':
          this.logResult('Decrease selected');
          console.log('decrease selected');
          this.btActionText = 'Subtract';
          break;
        default:
          throw Error('Invalid option: ' + selectedOption);
      }
    },
    changeCount(value) {
      switch (this.btActionText) {
        case 'Add':
          this.count += value;
          break;
        case 'Subtract':
          this.count -= value;
          break;
        default:
          throw Error('Invalid btActionText: ' + btActionText);
      }
      this.logResult(
        this.btActionText + ' ' + value + ' Result: ' + this.count
      );
    },
    resetCount() {
      console.log('Reset all');
      this.consoleOut = '';
      this.count = 0;
    },
    dblClickReset() {
      document.getElementById('select-action').value = 'increase';
      // To Remove in order to create bug
      this.btActionText = 'Add';
    },
  },
  computed: {
    // change construction a little bit
    hoverResult() {
      switch (true) {
        case this.count < 0:
          if (this.count === -30) {
            return 'It is -30';
          } else if (this.count < -30) {
            return 'Too few!';
          }
          return 'Not -30 yet';
        case this.count >= 0:
          if (this.count === 30) {
            return 'It is 30';
          } else if (this.count > 30) {
            return 'Too much!';
          }
          return 'Not 30 yet';
      }
    },
  },
}).mount('#assignment');

/*
How to test? 
1. Default states
2. Hover states css styles
3. Add
4. Subtract
5. Reset
6. Double click reset
7. Change select option
8. Verify logs
9. Verify timeouts
10. Verify different text for buttons on hover state
*/
