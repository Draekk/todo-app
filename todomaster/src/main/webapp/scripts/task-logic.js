//-----------ADD TASK

const $inputTask = document.getElementById('newTask');
const $btnAddTask = document.getElementById('btnAddTask');

$btnAddTask.addEventListener('click', () => {
  if($inputTask.value !== ''.trim()){
    let task = {  description: $inputTask.value.trim() };
    console.log(task);
    
    fetch('SvTask', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(task)
    })
    .then(response => response.json())
    .then(task => console.log(task))
    /*.then(fetch('SvTask')
      .then(response => response.json())
      .then(data => console.log(data))
      .catch(err => console.error(err)))*/
    .catch(err => console.error(err));

  }

})

