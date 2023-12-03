//-------------LOAD TASK

async function fetchData() {
  try {
    const response = await fetch('SvTask');
    const data = await response.json();
    return data;
  } catch (err) {
    console.error(err);
  }
}

async function loadTasks(){
  try {
    const list = await fetchData();
    showTask(list);
  } catch (error) {
    throw new Error('Error fetch in data: ' + error.message);
  }
}

function showTask(taskList) {
  const $slider = document.getElementById('slider');
  const $fragment = document.createDocumentFragment();
  taskList.forEach((task) => {
    createTask(task, $fragment);
  });

  $slider.appendChild($fragment);
}

function createTask(task, element){
  const $li = document.createElement('li');
    $li.classList.add('task');
    $li.classList.add('container');
    $li.classList.add('c-center-sb');

    const $div = document.createElement('div');
    $div.classList.add('container');

    const $input = document.createElement('input');
    $input.type = 'checkbox';
    $input.name = 'checkbox';
    $input.id = task.id;

    const $label = document.createElement('label');
    $label.title = task.description;
    $label.htmlFor = $input.id;
    $label.innerText = task.description;

    const $btnContainer = document.createElement('div');
    $btnContainer.classList.add('container');
    $btnContainer.classList.add('c-center-sb');

    const $btnEdit = document.createElement('button');
    $btnEdit.id = 'btnEdit';

    const $iconEdit = document.createElement('i');
    $iconEdit.classList.add('fa-solid');
    $iconEdit.classList.add('fa-pen');

    const $btnDelete = document.createElement('button');
    $btnDelete.id = 'btnDelete';

    const $iconDelete = document.createElement('i');
    $iconDelete.classList.add('fa-solid');
    $iconDelete.classList.add('fa-x');
    
    //Append childs
    $div.appendChild($input);
    $div.appendChild($label);
    $btnEdit.appendChild($iconEdit);
    $btnDelete.appendChild($iconDelete);
    $btnContainer.appendChild($btnEdit);
    $btnContainer.appendChild($btnDelete);
    $li.appendChild($div);
    $li.appendChild($btnContainer);
    element.appendChild($li);
}

loadTasks();


//-----------ADD TASK

const $inputTask = document.getElementById('newTask');
const $btnAddTask = document.getElementById('btnAddTask');

$btnAddTask.addEventListener('click', () => {
  if($inputTask.value !== ''.trim()){
    let task = {  description: $inputTask.value.trim() };
    
    fetch('SvTask', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(task)
    })
    .then(response => {
      return response.json();
    })
    .then(task => {
      $inputTask.value = '';
      const $slider = document.getElementById('slider');
      const $fragment = document.createDocumentFragment();
      createTask(task, $fragment);
      $slider.appendChild($fragment);
    })
    .catch(err => console.error(err));
  }
});



