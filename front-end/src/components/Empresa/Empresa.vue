<template>
  <div>
  <modal
    ref="modalRegistration"
    :title="'Empresa'"
    :save="save"
    :minHeight="'50vh'"
    :minWidth="'55vw'">
    <q-input
      v-model="entidade.nome"
      :float-label="'Nome'"
      name="nome"
      type="text"
      class="full-width"/>
    <q-input
      v-model="entidade.cnpj"
      :float-label="'CNPJ'"
      name="cnpj"
      type="text"
      class="col-sm-4"/>
      <div class="row col-sm-12">
        <q-input class="col-sm-6"
          v-model="entidade.telefone"
          :float-label="'Telefone'"
          name="telefone"
          type="text"/>
        <q-input
          v-model="entidade.telefone2"
          :float-label="'Telefone Opcional'"
          name="telefone2"
          class="col-sm-6"
          type="text"/>
      </div>
      <div class="row col-sm-12">
        <q-input
          v-model="entidade.email"
          :float-label="'E-mail'"
          name="email"
          class="col-sm-6"
          type="text"/>
        <q-input
          v-model="entidade.site"
          :float-label="'Site'"
          name="site"
          class="col-sm-6"
          type="text"/>
        </div>
  </modal>
  <q-btn color="blue" @click.native="novoItem()" icon="library_add" label="Novo" />
  <br/>
  <br/>
  <b>Manutenção de Empresa</b>
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
      <q-td key="nome" :props="props">
        <span>{{ props.row.nome }}</span>
      </q-td>
      <q-td key="cnpj" :props="props">
        <span>{{ props.row.cnpj }}</span>
      </q-td>
      <q-td key="site" :props="props">
        <span>{{ props.row.site }}</span>
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
          name: 'nome',
          required: true,
          label: 'Nome',
          align: 'left',
          field: 'nome',
          sortable: true
        },
        {
          name: 'cnpj',
          required: true,
          label: 'C.N.P.J',
          align: 'left',
          field: 'cnpj',
          sortable: true
        },
        {
          name: 'site',
          required: true,
          label: 'Site',
          align: 'left',
          field: 'site',
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
        url: 'http://localhost:8081/empresa/',
        data: this.entidade
      })
        .then(response => {
          this.$q.notify({message: 'Salvo com Sucesso.', type: 'positive', icon: 'sentiment_satisfied_alt'})
          this.getEmpresas()
          this.$refs.modalRegistration.close()
        })
        .catch(error => {
          this.$q.notify({message: error.response.data[0].mensagemUsuario, type: 'negative', icon: 'error'})
        })
    },
    getEmpresas () {
      axios({
        method: 'get',
        url: 'http://localhost:8081/empresa/'
      })
        .then(response => {
          this.tableData = response.data.map(el => ({
            id: el.id, nome: el.nome, cnpj: el.cnpj, telefone: el.telefone, telefone2: el.telefone2, site: el.site, email: el.email
          }))
        })
        .catch(() => {
          this.$q.notify({message: 'Não foi possível buscar os dados.', type: 'negative', icon: 'error'})
        })
    },
    deleteRow (row) {
      this.$q.notify({
        color: 'red',
        message: `Deseja realmente remover ${row.nome}?.`,
        actions: [{
          label: 'Sim',
          handler: () => {
            axios({
              method: 'delete',
              url: `http://localhost:8081/empresa/ ${row.id}`
            })
              .then(response => {
                this.$q.notify({message: `${row.nome} excluído com sucesso.`, type: 'positive', icon: 'done'})
                this.getEmpresas()
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
    this.getEmpresas()
  }
}
</script>
