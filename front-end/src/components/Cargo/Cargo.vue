<template>
  <div>
  <modal
    ref="modalRegistration"
    :title="'Cargo'"
    :save="save"
    :minHeight="'30vh'"
    :minWidth="'35vw'">
    <q-input
      v-model="entidade.descricao"
      :float-label="'Descricao'"
      name="descricao"
      maxlength="100"
      type="text"
      class="full-width"/>
  </modal>
  <q-btn color="blue" @click.native="novoItem()" icon="library_add" label="Novo" />
  <br/>
  <br/>
  <b>Manutenção de Cargos</b>
  <q-table
    :data="tableData"
    :columns="columns"
    selection="single"
    row-key="id"
    color="secondary"
    :no-data-label="'dataTable.noDataLabel'"
    :rowsPerPageLabel="'Linhas por páginas'"
  >
    <q-tr slot="body" slot-scope="props" :props="props">
      <q-tr :props="props">
        <q-btn size="sm" round dense color="secondary" flat icon="edit" @click.native="editItem(props.row)"  class="q-mr-xs" />
        <q-btn size="sm" round dense color="red" flat icon="delete" @click.native="deleteRow(props.row)" class="q-mr-xs" />
      </q-tr>
      <q-td key="id" :props="props">
        <span>{{ props.row.id }}</span>
      </q-td>
      <q-td key="descricao" :props="props">
        <span>{{ props.row.descricao }}</span>
      </q-td>
    </q-tr>
  </q-table>
  </div>
</template>

<script>
import axios from 'axios'
import modal from '../modal.vue'

export default {
  components: {
    modal
  },
  data () {
    return {
      columns: [
        {
          name: 'id',
          required: true,
          label: 'Código',
          align: 'left',
          field: 'name',
          sortable: true
        },
        {
          name: 'descricao',
          required: true,
          label: 'Descrição',
          align: 'left',
          field: 'name',
          sortable: true
        }
      ],
      tableData: [],
      dark: true,
      separator: 'horizontal',
      entidade: {}
    }
  },
  opened: false,
  computed: {
    tableClass () {
      if (this.dark) {
        return 'bg-black'
      }
    }
  },
  methods: {
    novoItem () {
      this.entidade = {}
      this.$refs.modalRegistration.open()
    },
    editItem (row) {
      this.entidade = Object.assign({}, row)
      this.$refs.modalRegistration.open()
    },
    save () {
      axios({
        method: 'post',
        url: 'http://localhost:8081/cargo/',
        data: this.entidade
      })
        .then(response => {
          this.$q.notify({message: 'Salvo com Sucesso.', type: 'positive', icon: 'sentiment_satisfied_alt'})
          this.getCargos()
          this.$refs.modalRegistration.close()
        })
        .catch(error => {
          this.$q.notify({message: error.response.data[0].mensagemUsuario, type: 'negative', icon: 'error'})
        })
    },
    getCargos () {
      axios({
        method: 'get',
        url: 'http://localhost:8081/cargo/'
      })
        .then(response => {
          this.tableData = response.data
        })
        .catch(() => {
          this.$q.notify({message: 'Não foi possível buscar os dados.', type: 'negative', icon: 'error'})
        })
    },
    deleteRow (row) {
      this.$q.notify({
        color: 'red',
        message: `Deseja realmente remover ${row.descricao}?.`,
        actions: [{
          label: 'Sim',
          handler: () => {
            axios({
              method: 'delete',
              url: `http://localhost:8081/cargo/ ${row.id}`
            })
              .then(response => {
                this.$q.notify({message: `${row.descricao} excluído com sucesso.`, type: 'positive', icon: 'done'})
                this.getCargos()
              })
              .catch(() => {
                this.$q.notify({message: 'Não foi possível deletar.', type: 'negative', icon: 'error'})
              })
          }
        }]
      })
    }
  },
  mounted () {
    this.getCargos()
  }
}
</script>
